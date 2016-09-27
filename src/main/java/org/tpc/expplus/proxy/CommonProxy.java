package org.tpc.expplus.proxy;

import org.tpc.expplus.ExpPlusMod;
import org.tpc.expplus.Properties;
import org.tpc.expplus.common.block.BlockExpFlower;
import org.tpc.expplus.common.itemblock.ItemBlockExpFlower;
import org.tpc.expplus.common.world.generator.ExpPlusWorldGenerator;
import org.tpc.expplus.util.ExpManager;

import net.minecraftforge.fml.common.registry.GameRegistry;

import static org.tpc.expplus.ExpPlusMod.DEBUG;

public class CommonProxy {

	private ExpManager expManager;

	public CommonProxy() {
		DEBUG(getClass().getSimpleName());

		expManager = new ExpManager();
	}

	public ExpManager getExpManager() {
		return expManager;
	}

	public void register() {

		// Blocks

		// GameRegistry.register((Properties.EXPTRADER = new
		// BlockExpTrader(Properties.EXPTRADER_ID)));
		// GameRegistry.registerTileEntity(TileExpTrader.class,
		// TileExpTrader.class.getSimpleName());
		// MinecraftForge.setBlockHarvestLevel(Properties.EXPTRADER, "pickaxe",
		// 2);

		GameRegistry.register(ExpPlusMod.BLOCK_EXPFLOWER = new BlockExpFlower());
		GameRegistry.register(ExpPlusMod.ITEMBLOCK_EXPFLOWER = new ItemBlockExpFlower(ExpPlusMod.BLOCK_EXPFLOWER));

		// registerGenerator();

		// Recipes
		// GameRegistry.addRecipe(new ItemStack(Properties.EXPTRADER, 1), new
		// Object[] { "C#C", "GLG", "OOO", 'C', Block.chest, '#', Block.glass,
		// 'G', Item.ingotGold, 'L', Block.blockLapis, 'O', Block.obsidian });

		// World Generation
		GameRegistry.registerWorldGenerator(new ExpPlusWorldGenerator(ExpPlusMod.BLOCK_EXPFLOWER,
				Properties.EXPFLOWER_GENERATION, Properties.EXPFLOWER_GROUP), 1);

		// GUI
		// NetworkRegistry.instance().registerGuiHandler(this, new
		// GuiHandler());

	}

	// public boolean isGamePaused() {
	// return false;
	// }
	//
	// public boolean isPlayerOnline(String name){
	// for(String player : MinecraftServer.getServer().getAllUsernames()) {
	// if(player.equals(name)) {
	// return true;
	// }
	// }
	// return false;
	// }
	//
	// public boolean isClient() {
	// return false;
	// }
	//
	// public void registerGenerator() {
	// if(!isBuildcraftInstalled()) {
	// return;
	// }
	//
	// try {
	// Class<? extends BlockExpPlus> expGeneratorClass =
	// getClass().getClassLoader().loadClass("mods.expplus.block.BlockExpGenerator").asSubclass(BlockExpPlus.class);
	// Class<? extends TileEntity> expGeneratorTileEntityClass =
	// getClass().getClassLoader().loadClass("mods.expplus.tileentity.TileExpGenerator").asSubclass(TileExpPlus.class);
	//
	// GameRegistry.registerBlock((Properties.EXPGENERATOR =
	// expGeneratorClass.getConstructor(Integer.TYPE).newInstance(Properties.EXPGENERATOR_ID)),
	// Properties.EXPGENERATOR_NAME);
	// GameRegistry.registerTileEntity(expGeneratorTileEntityClass,
	// expGeneratorTileEntityClass.getSimpleName());
	// MinecraftForge.setBlockHarvestLevel(Properties.EXPGENERATOR, "pickaxe",
	// 2);
	// } catch (ClassNotFoundException e) {
	// e.printStackTrace();
	// } catch (InstantiationException e) {
	// e.printStackTrace();
	// } catch (IllegalAccessException e) {
	// e.printStackTrace();
	// } catch (IllegalArgumentException e) {
	// e.printStackTrace();
	// } catch (InvocationTargetException e) {
	// e.printStackTrace();
	// } catch (NoSuchMethodException e) {
	// e.printStackTrace();
	// } catch (SecurityException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// public boolean isBuildcraftInstalled() {
	// return Loader.isModLoaded("BuildCraft|Energy") &&
	// Loader.isModLoaded("BuildCraft|Core");
	// }
}