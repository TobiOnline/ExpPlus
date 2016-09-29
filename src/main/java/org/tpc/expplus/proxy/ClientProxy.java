package org.tpc.expplus.proxy;

import org.tpc.expplus.ExpPlus;
import org.tpc.expplus.Properties;
import org.tpc.expplus.client.render.tile.TileRenderExpTrader;
import org.tpc.expplus.common.tile.TileExpTrader;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

	private TileRenderExpTrader tileRenderExpTrader;
	
	public ClientProxy() {
		super();
	}

	@Override
	public void register() {
		super.register();
		
		ItemModelMesher itemModelMesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();

		itemModelMesher.register(ExpPlus.ITEMBLOCK_EXPFLOWER, 0,
				new ModelResourceLocation(Properties.RESOURCE_BLOCK_EXPFLOWER, "inventory"));
		itemModelMesher.register(ExpPlus.ITEMBLOCK_EXPTRADER, 0,
				new ModelResourceLocation(Properties.RESOURCE_BLOCK_EXPTRADER, "inventory"));
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileExpTrader.class, tileRenderExpTrader = new TileRenderExpTrader());
	}
	
	@Override
	public void onModelBake(ModelBakeEvent event) {
		tileRenderExpTrader.init();
		// Need a method to pre-load a model without a block state
	}
}
