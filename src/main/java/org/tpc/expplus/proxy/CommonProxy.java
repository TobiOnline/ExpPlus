package org.tpc.expplus.proxy;

import org.tpc.expplus.ExpPlus;
import org.tpc.expplus.Properties;
import org.tpc.expplus.common.block.BlockExpFlower;
import org.tpc.expplus.common.block.BlockExpTrader;
import org.tpc.expplus.common.generator.ExpPlusWorldGenerator;
import org.tpc.expplus.common.gui.GuiHandler;
import org.tpc.expplus.common.itemblock.ItemBlockExpFlower;
import org.tpc.expplus.common.itemblock.ItemBlockExpTrader;
import org.tpc.expplus.common.tile.TileExpTrader;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static org.tpc.expplus.ExpPlus.DEBUG;

public class CommonProxy {

	public CommonProxy() {
		DEBUG(getClass().getSimpleName());
	}
	
	public void register() {

		// Blocks
		GameRegistry.register(ExpPlus.BLOCK_EXPFLOWER = new BlockExpFlower());
		GameRegistry.register(ExpPlus.ITEMBLOCK_EXPFLOWER = new ItemBlockExpFlower(ExpPlus.BLOCK_EXPFLOWER));

		GameRegistry.register(ExpPlus.BLOCK_EXPTRADER = new BlockExpTrader());
		GameRegistry.register(ExpPlus.ITEMBLOCK_EXPTRADER = new ItemBlockExpTrader(ExpPlus.BLOCK_EXPTRADER));
		GameRegistry.registerTileEntity(TileExpTrader.class, TileExpTrader.class.getSimpleName());

		// Recipes
		GameRegistry.addRecipe(new ItemStack(ExpPlus.ITEMBLOCK_EXPTRADER, 1),
				new Object[] { "C#C", "GLG", "OOO", 'C', Blocks.CHEST, '#', Blocks.GLASS, 'G', Items.GOLD_INGOT, 'L',
						Blocks.LAPIS_BLOCK, 'O', Blocks.OBSIDIAN });

		// World Generation
		GameRegistry.registerWorldGenerator(new ExpPlusWorldGenerator(ExpPlus.BLOCK_EXPFLOWER,
				Properties.DEFAULT_EXPFLOWER_GENERATION, Properties.DEFAULT_EXPFLOWER_GROUP), 1);

		// GUI
		NetworkRegistry.INSTANCE.registerGuiHandler(ExpPlus.instance, new GuiHandler());
	}

	public void onModelBake(ModelBakeEvent event) { }
}