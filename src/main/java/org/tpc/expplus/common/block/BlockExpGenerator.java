package org.tpc.expplus.common.block;
//package mods.expplus.block;
//
//import static mods.expplus.ExpPlus.DEBUG;
//import mods.expplus.ExpPlus;
//import mods.expplus.Properties;
//import mods.expplus.network.packets.PacketAction;
//import mods.expplus.network.packets.PacketActionGlobal;
//import mods.expplus.tileentity.TileExpChest;
//import mods.expplus.tileentity.TileExpGenerator;
//import mods.expplus.tileentity.TileExpPlus;
//import net.minecraft.client.renderer.texture.IconRegister;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.network.packet.Packet132TileEntityData;
//import net.minecraft.network.packet.Packet250CustomPayload;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.DamageSource;
//import net.minecraft.world.World;
//import cpw.mods.fml.common.network.PacketDispatcher;
//import cpw.mods.fml.common.network.Player;
//import cpw.mods.fml.relauncher.Side;
//import cpw.mods.fml.relauncher.SideOnly;
//
//public class BlockExpGenerator extends BlockExpPlus {
//
//	public BlockExpGenerator(int id) {
//		super(id);
//	}
//
//	@Override
//	public TileEntity createNewTileEntity(World world) {
//		return new TileExpGenerator();
//	}
//
//	@Override
//	protected void onBlockRemoved(World world, EntityPlayer player, int x, int y, int z, TileExpPlus tileEntity) { }
//
//	@Override
//	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
//		if (super.onBlockActivated(world, x, y, z, player, par6, par7, par8, par9)) {
//			if(world.isRemote) {
//				return true; // Handled by server
//			}
//			
//			TileExpPlus tile = (TileExpPlus) world.getBlockTileEntity(x, y, z);
//			if(tile == null) {
//				DEBUG("Tile Entity is NULL! Called onBlockActivated() of ExpGenerator!");
//				return true;
//			}
//			
//			String owner = tile.getOwner();
//			if(owner == null) {
//				DEBUG("Tile Entity's owner is NULL! Called onBlockActivated() of ExpGenerator!");
//				return true;
//			}
//			
//			if (owner.equals(player.username)) {
//				PacketDispatcher.sendPacketToPlayer(new PacketActionGlobal("SetGlobalExperience", player.username, String.valueOf(player.experienceTotal)).createPacket(), (Player) player);
//				
//				// Open GUI on Server and Client
//				player.openGui(ExpPlus.instance, Properties.GUI_EXPGENERATOR, world, x, y, z);
//			} else {
//				player.attackEntityFrom(DamageSource.generic, 1);
//			}
//			return true;
//		}
//		return false;
//	}
//
//	@Override
//	public void onNeighborBlockChange(World world, int x, int y, int z, int id) {
//		TileExpGenerator tile = (TileExpGenerator) world.getBlockTileEntity(x, y, z);
//
//		if (tile == null) {
//			return;
//		}
//
//		updatePower(world, x, y, z);
//		updateConnections(tile, world, x, y, z);
//	}
//
//	public void updatePower(World world, int x, int y, int z) {
//		TileExpGenerator tile = (TileExpGenerator) world.getBlockTileEntity(x, y, z);
//
//		if (tile == null) {
//			return;
//		}
//
//		boolean powered = world.isBlockIndirectlyGettingPowered(x, y, z) || world.isBlockIndirectlyGettingPowered(x, y + 1, z);
//		tile.setPowered(powered);
//		
//		// TODO
////		PacketDispatcher.sendPacketToAllPlayers(new Packet250CustomPayload("Powered", byte[] { (byte) (powered ? 1 : 0) }));
////
////		new PacketAction(, new String(n), tile).sendPacketToAllPlayer();
//	}
//
//	public void updateConnections(TileExpGenerator tile, World world, int x, int y, int z) {
//		tile.updateConnections();
//
//		new PacketAction("BlockUpdate", new String(new byte[] { (byte) (tile.getConnections()[0] ? 1 : 0), (byte) (tile.getConnections()[1] ? 1 : 0), (byte) (tile.getConnections()[2] ? 1 : 0), (byte) (tile.getConnections()[3] ? 1 : 0) }), tile)
//				.sendPacketToAllPlayer();
//	}
//
//	@Override
//	@SideOnly(Side.CLIENT)
//	public void registerIcons(IconRegister iconRegister) {
//		super.registerIcons(iconRegister);
//
//		Properties.EXP_FLUID = iconRegister.registerIcon("ExpPlus:expliquid");
//	}
//
//}
