package org.tpc.expplus.client.render.tile;

import org.lwjgl.opengl.GL11;
import org.tpc.expplus.Properties;
import org.tpc.expplus.client.render.models.ModelExpTraderAnimation;
import org.tpc.expplus.common.tile.TileExpTrader;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockModelRenderer;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.ModelManager;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class TileRenderExpTrader extends TileEntitySpecialRenderer<TileExpTrader> {

	private static final float ROTATION_SPEED = 1.0F;
	private static final float ANIMATION_SPEED = 0.05F;
	private static final float ANIMATION_SIZE = 0.75F;
	private static final float XP_ORB_COLOR_SPEED = 0.5F;

	private static final BlockPos NULL_OFFSET = new BlockPos(0, 0, 0);
	private static final ResourceLocation EXP_ORB_TEXTURES = new ResourceLocation("textures/entity/experience_orb.png");
	private static final ModelResourceLocation BASE_LOCATION = new ModelResourceLocation(
			Properties.RESOURCE_BLOCK_EXPTRADER, "normal");
	private static final ModelExpTraderAnimation animationModel = new ModelExpTraderAnimation();

	public TileRenderExpTrader() { }

	@Override
	public void renderTileEntityAt(TileExpTrader tile, double x, double y, double z, float partialTicks,
			int destroyStage) {
		double time = partialTicks + tile.getWorld().getTotalWorldTime();
		float rotationAngle = (float) ((tile.rotation + time * ROTATION_SPEED) % 360.0D);
		float animation = (float) ((tile.animation + time * ANIMATION_SPEED) % 2.0D);
		animation = (((animation > 1.0F) ? 2.0F - animation : animation) - 0.5F) * ANIMATION_SIZE;

		int red = (int) ((MathHelper.sin((float) (time * XP_ORB_COLOR_SPEED)) + 1.0F) * 0.5F * 255.0F);
		int blue = (int) ((MathHelper.sin((float) (time * XP_ORB_COLOR_SPEED + 4.1887903F)) + 1.0F) * 0.1F * 255.0F);

		World world = tile.getWorld();
		BlockPos position = new BlockPos(x, y, z);
		IBlockState state = world.getBlockState(position);

		Minecraft minecraft = Minecraft.getMinecraft();
		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer buffer = tessellator.getBuffer();

		RenderManager renderManager = minecraft.getRenderManager();
		TextureManager texManager = renderManager.renderEngine;
		BlockRendererDispatcher rendererDispatcher = minecraft.getBlockRendererDispatcher();
		BlockModelRenderer renderer = rendererDispatcher.getBlockModelRenderer();
		ModelManager manager = rendererDispatcher.getBlockModelShapes().getModelManager();

		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);

		texManager.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

		buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
		renderer.renderModel(world, manager.getModel(BASE_LOCATION), state, NULL_OFFSET, buffer, false,
				MathHelper.getPositionRandom(position));
		tessellator.draw();

		GlStateManager.pushMatrix();
		GlStateManager.translate(0.5F, 0.5F, 0.5F);
		GlStateManager.rotate(rotationAngle, 0.0F, 1.0F, 0.0F);

		texManager.bindTexture(Properties.RESOURCE_TEXURE_ANIMATIONS);
		animationModel.render(1.0F / 16.0F);

		// Draw the experience orb
		GlStateManager.popMatrix();
		RenderHelper.enableStandardItemLighting();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

		GlStateManager.rotate(rotationAngle, 0.0F, 1.0F, 0.0F);
		GlStateManager.translate(animation, 0.0F, 0.0F);
		GlStateManager.rotate(-rotationAngle, 0.0F, 1.0F, 0.0F);

		GlStateManager.translate(0.5F, 11.0F / 16.0F, 0.5F);

		GlStateManager.rotate(180.0F - renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate((float) (renderManager.options.thirdPersonView == 2 ? 1 : -1) * renderManager.playerViewX,
				1.0F, 0.0F, 0.0F);
		GlStateManager.scale(0.3F, 0.3F, 0.3F);

		texManager.bindTexture(EXP_ORB_TEXTURES);
		buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);
		buffer.pos(-0.5D, -0.5D, 0.0D).tex(0.0D, 0.25D).color(red, 255, blue, 128).normal(0.0F, 1.0F, 0.0F).endVertex();
		buffer.pos(0.5D, -0.5D, 0.0D).tex(0.25D, 0.25D).color(red, 255, blue, 128).normal(0.0F, 1.0F, 0.0F).endVertex();
		buffer.pos(0.5D, 0.5D, 0.0D).tex(0.25D, 0.0D).color(red, 255, blue, 128).normal(0.0F, 1.0F, 0.0F).endVertex();
		buffer.pos(-0.5D, 0.5D, 0.0D).tex(0.0D, 0.0D).color(red, 255, blue, 128).normal(0.0F, 1.0F, 0.0F).endVertex();
		tessellator.draw();

		GlStateManager.disableBlend();
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
	}
}