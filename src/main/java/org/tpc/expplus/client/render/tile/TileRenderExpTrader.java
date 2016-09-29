package org.tpc.expplus.client.render.tile;

import org.lwjgl.opengl.GL11;
import org.tpc.expplus.Properties;
import org.tpc.expplus.common.tile.TileExpTrader;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockModelRenderer;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.ModelManager;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import static org.tpc.expplus.ExpPlus.DEBUG;

public class TileRenderExpTrader extends TileEntitySpecialRenderer<TileExpTrader> {

//	private static final float EXP_SIZE = 0.3F;
//	private static final float ANIMATION_SIZE = 0.5F;
	private static final BlockPos NULL_OFFSET = new BlockPos(0, 0, 0);
	
	ModelResourceLocation baseLocation;
	ModelResourceLocation topLocation;

	public TileRenderExpTrader() {
		baseLocation = new ModelResourceLocation(Properties.RESOURCE_BLOCK_EXPTRADER, "normal");
		topLocation = new ModelResourceLocation(Properties.RESOURCE_MODEL_EXPTRADER_ANIMATION, "normal");
	}

	@Override
	public void renderTileEntityAt(TileExpTrader tile, double x, double y, double z, float partialTicks,
			int destroyStage) {
		World world = tile.getWorld();
		BlockPos position = new BlockPos(x, y, z);
		IBlockState state = world.getBlockState(position);
		
		Minecraft minecraft = Minecraft.getMinecraft();
		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer buffer = tessellator.getBuffer();
		
		TextureManager texManager = minecraft.getRenderManager().renderEngine;
		BlockRendererDispatcher rendererDispatcher = minecraft.getBlockRendererDispatcher();
		BlockModelRenderer renderer = rendererDispatcher.getBlockModelRenderer();
		ModelManager manager = rendererDispatcher.getBlockModelShapes().getModelManager();
		
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		
		texManager.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		
		buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
		renderer.renderModel(world, manager.getModel(baseLocation), state, NULL_OFFSET, buffer, false, MathHelper.getPositionRandom(position));
		tessellator.draw();
		
		GlStateManager.rotate(tile.rotation, 0.0F, 1.0F, 0.0F);
		GlStateManager.translate(0.0F, 0.5F, 0.0F);
		
		buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
		renderer.renderModel(world, manager.getModel(topLocation), state, NULL_OFFSET, buffer, false, MathHelper.getPositionRandom(position));
		tessellator.draw();

		GlStateManager.popMatrix();
	}

	public void init() {
		DEBUG(baseLocation);
		DEBUG(topLocation);
	}
}