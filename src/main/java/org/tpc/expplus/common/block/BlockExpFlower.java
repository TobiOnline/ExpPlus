package org.tpc.expplus.common.block;

import java.util.ArrayList;
import java.util.List;

import org.tpc.expplus.ExpPlusMod;
import org.tpc.expplus.Properties;

import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

import static org.tpc.expplus.ExpPlusMod.DEBUG;

public class BlockExpFlower extends BlockBush implements IShearable {

	protected static final AxisAlignedBB TALL_GRASS_AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D,
			0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);

	public BlockExpFlower() {
		super();

		setCreativeTab(CreativeTabs.DECORATIONS);
		setRegistryName(Properties.BLOCK_EXPFLOWER);
		setUnlocalizedName(Properties.EXPFLOWER_NAME);
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

		// 1 to max-drop
		int experience = ExpPlusMod.RANDOM.nextInt(Properties.EXPFLOWER_MAXDROP) + 1;
		
		DEBUG("Dropping " + experience + " Experience from ExpFlower!");

		while (experience > 0) {
			int exp = EntityXPOrb.getXPSplit(experience);
			experience -= exp;
			worldIn.spawnEntityInWorld(new EntityXPOrb(worldIn, (double) pos.getX() + 0.5D, (double) pos.getY() + 0.75D,
					(double) pos.getZ() + 0.5D, exp));
		}
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
