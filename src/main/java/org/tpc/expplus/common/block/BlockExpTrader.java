package org.tpc.expplus.common.block;

import java.util.UUID;

import org.tpc.expplus.ExpPlus;
import org.tpc.expplus.Properties;
import org.tpc.expplus.common.tile.TileExpTrader;
import org.tpc.expplus.util.ExpUtil;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class BlockExpTrader extends BlockExpPlus {

	public BlockExpTrader() {
		setCreativeTab(CreativeTabs.REDSTONE);
		setRegistryName(Properties.RESOURCE_BLOCK_EXPTRADER);
		setUnlocalizedName(Properties.NAME_EXPTRADER);
		setSoundType(SoundType.STONE);
		
		setHarvestLevel("pickaxe", 2);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote)
			return true;
		
		if (super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ)) {
			TileEntity tile = worldIn.getTileEntity(pos);
			if ((tile == null) || !(tile instanceof TileExpTrader))
				return false;
			
			TileExpTrader trader = (TileExpTrader) tile;
			synchronized (trader.getLockObject()) {
				UUID playerID = trader.getInUseBy();
				if ((playerID != null) && (worldIn.getPlayerEntityByUUID(playerID) != null)) {
					playerIn.addChatMessage(new TextComponentTranslation(Properties.MESSAGE_IN_USE, playerID.toString()));
					return true;
				}
				
				trader.setInUseBy(playerIn.getUniqueID());
			}
			
			// Fix for minecraft not updating exp
			ExpUtil.recalculateExp(playerIn);
			playerIn.openGui(ExpPlus.instance, Properties.GUI_ID_EXPTRADER, worldIn, pos.getX(), pos.getY(), pos.getZ());
			return true;
		}
		
		return false;
	}
	
	@Override
	public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
		if (worldIn.isRemote)
			return;
		
		TileEntity tile = worldIn.getTileEntity(pos);
		if ((tile == null) || !(tile instanceof TileExpTrader))
			return;
		
		TileExpTrader trader = (TileExpTrader) tile;
		dropExp(trader.getOwnerExperience(), worldIn, pos);
		//dropItem(expTrader.getStackInSlot(1), worldIn, pos);
		
		super.onBlockDestroyedByPlayer(worldIn, pos, state);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileExpTrader();
	}
}
