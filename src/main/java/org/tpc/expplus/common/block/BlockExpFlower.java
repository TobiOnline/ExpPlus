package org.tpc.expplus.common.block;

import java.util.ArrayList;
import java.util.List;

import org.tpc.expplus.ExpPlus;
import org.tpc.expplus.Properties;

import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

import static org.tpc.expplus.ExpPlus.DEBUG;

public class BlockExpFlower extends BlockBush implements IShearable {

	protected static final AxisAlignedBB TALL_GRASS_AABB = new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 0.8D, 0.9D);

	public BlockExpFlower() {
		setCreativeTab(CreativeTabs.DECORATIONS);
		setRegistryName(Properties.RESOURCE_BLOCK_EXPFLOWER);
		setUnlocalizedName(Properties.NAME_EXPFLOWER);
		setSoundType(SoundType.PLANT);
	}

	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
		list.add(new ItemStack(this));
	}

	@Override
	public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
		if (worldIn.isRemote)
			return;

		int experience = ExpPlus.RANDOM.nextInt(Properties.DEFAULT_EXPFLOWER_MAXDROP) + 1;
		DEBUG("Dropping " + experience + " Experience from ExpFlower!");
		dropXpOnBlockBreak(worldIn, pos, experience);
	}

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
		return (item != null) && (item.getItem() == Items.SHEARS);
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();

		stack.add(new ItemStack(this, 1));

		return stack;
	}

	@Override
	public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player) {
		return false;
	}
}
