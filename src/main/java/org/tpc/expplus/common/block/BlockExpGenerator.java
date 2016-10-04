package org.tpc.expplus.common.block;

import org.tpc.expplus.ExpPlus;
import org.tpc.expplus.Properties;
import org.tpc.expplus.common.tile.TileExpGenerator;
import org.tpc.expplus.common.tile.TileExpGenerator.State;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockExpGenerator extends BlockExpPlus {

	static {
		ExpPlus.DEBUG("Loading class BlockExpGenerator!");
	}
	
	public BlockExpGenerator() {
		setCreativeTab(CreativeTabs.REDSTONE);
		setRegistryName(Properties.RESOURCE_BLOCK_EXPGENERATOR);
		setUnlocalizedName(Properties.NAME_EXPGENERATOR);
		setSoundType(SoundType.STONE);
		
		setHarvestLevel("pickaxe", 2);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileExpGenerator();
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
//		if (worldIn.isRemote)
//			return true;
		
		if (super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ)) {
			TileEntity tile = worldIn.getTileEntity(pos);
			if ((tile == null) || !(tile instanceof TileExpGenerator))
				return false;
			
			// Fix for minecraft not updating exp
			// ExpUtil.recalculateExp(playerIn);
			// playerIn.openGui(ExpPlus.instance, Properties.GUI_ID_EXPGENERATOR, worldIn, pos.getX(), pos.getY(), pos.getZ());
			TileExpGenerator generator = ((TileExpGenerator) tile);
			switch(generator.getState()) {
			case Activating:
			case Active:
				generator.setState(State.Deactivating);
				break;
			case Deactivating:
			case Inactive:
				generator.setState(State.Activating);
				break;
			}
			return true;
		}
		
		return false;
	}
	
	@Override
	public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
		if (worldIn.isRemote)
			return;
		
		TileEntity tile = worldIn.getTileEntity(pos);
		if ((tile == null) || !(tile instanceof TileExpGenerator))
			return;
		
		dropXpOnBlockBreak(worldIn, pos, ((TileExpGenerator) tile).getExperience());
		
		super.onBlockDestroyedByPlayer(worldIn, pos, state);
	}
}
