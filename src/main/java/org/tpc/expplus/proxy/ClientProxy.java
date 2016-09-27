package org.tpc.expplus.proxy;

import org.tpc.expplus.ExpPlusMod;
import org.tpc.expplus.Properties;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

public class ClientProxy extends CommonProxy {

	public ClientProxy() {
		super();
	}

	@Override
	public void register() {
		super.register();
		
		ItemModelMesher itemModelMesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();

		itemModelMesher.register(ExpPlusMod.ITEMBLOCK_EXPFLOWER, 0,
				new ModelResourceLocation(Properties.BLOCK_EXPFLOWER, "inventory"));
	}
}
