package org.tpc.expplus.common.gui;

import org.tpc.expplus.Properties;
import org.tpc.expplus.client.gui.GuiExpTrader;
import org.tpc.expplus.common.inventory.container.ContainerExpTrader;
import org.tpc.expplus.common.tile.TileExpPlus;
import org.tpc.expplus.common.tile.TileExpTrader;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import static org.tpc.expplus.ExpPlus.DEBUG;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		DEBUG("Opening GUI on server: " + ID);
		
		TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));
		if ((tile == null) || !(tile instanceof TileExpPlus))
			return null;
		
		switch (ID) {
		case Properties.GUI_ID_EXPTRADER:
			if (!(tile instanceof TileExpTrader))
				return null;
			
			return new ContainerExpTrader(player, (TileExpTrader) tile);
		default:
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		DEBUG("Opening GUI on client: " + ID);
		
		TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));
		if ((tile == null) || !(tile instanceof TileExpPlus))
			return null;
		
		switch (ID) {
		case Properties.GUI_ID_EXPTRADER:
			if (!(tile instanceof TileExpTrader))
				return null;
			
			return new GuiExpTrader(new ContainerExpTrader(player, (TileExpTrader) tile));
		default:
			return null;
		}
	}
}