package org.tpc.expplus.client.render.tile;

import org.lwjgl.opengl.GL11;
import org.tpc.expplus.Properties;
import org.tpc.expplus.client.render.models.ModelExpGeneratorAnimation;
//import org.tpc.expplus.client.render.models.ModelExpGeneratorAnimation;
import org.tpc.expplus.common.tile.TileExpGenerator;
import org.tpc.expplus.common.tile.TileExpGenerator.State;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockModelRenderer;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.ModelManager;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import static org.tpc.expplus.ExpPlus.DEBUG;

public class TileRenderExpGenerator extends TileEntitySpecialRenderer<TileExpGenerator> {

	private static final float ROTATION_SPEED = 15.0F;
	private static final float ANIMATION_SPEED = 0.005F;
	private static final float ANIMATION_DIVIDER = 0.5F;

	private static final BlockPos NULL_OFFSET = new BlockPos(0, 0, 0);
	private static final ModelResourceLocation BASE_LOCATION = new ModelResourceLocation(
			Properties.RESOURCE_BLOCK_EXPGENERATOR, "normal");
	private static final ModelExpGeneratorAnimation MODEL_ANIMATION = new ModelExpGeneratorAnimation();

	public TileRenderExpGenerator() { }

	@Override
	public void renderTileEntityAt(TileExpGenerator tile, double x, double y, double z, float partialTicks,
			int destroyStage) {
		double time = partialTicks + tile.getWorld().getTotalWorldTime();
		if (tile.time < 0.0D) {
			tile.time = time;
		}
		double deltaTime = time - tile.time; 
		tile.time = time;
		
		float rotationFactor;
		switch (tile.getState()) {
		case Active:
			tile.animation = 1.0F;
			rotationFactor = 1.0F;
			break;
		case Activating:
			tile.animation += (float) Math.max(Math.min(deltaTime * ANIMATION_SPEED, 1.001D), -0.001D);
			if (tile.animation >= 1.0F) {
				tile.setState(State.Active);
				rotationFactor = 1.0F;
			} else if (tile.animation >= ANIMATION_DIVIDER) {
				rotationFactor = (tile.animation - ANIMATION_DIVIDER) / (1 - ANIMATION_DIVIDER);
			} else {
				rotationFactor = 0.0F;
			}
			break;
		case Deactivating:
			tile.animation -= (float) Math.max(Math.min(deltaTime * ANIMATION_SPEED, 1.001D), -0.001D);
			if (tile.animation <= 0.0F) {
				tile.setState(State.Inactive);
				rotationFactor = 0.0F;
			} else if (tile.animation >= ANIMATION_DIVIDER) {
				rotationFactor = (tile.animation - ANIMATION_DIVIDER) / (1 - ANIMATION_DIVIDER);
			} else {
				rotationFactor = 0.0F;
			}
			break;
		default:
			tile.animation = 0.0F;
			rotationFactor = 0.0F;
			break;
		}

		if ((rotationFactor > 0.0F) && (rotationFactor < 1.0F)) {
			DEBUG("Generator Rotation: " + rotationFactor);
		}
		tile.rotation += (deltaTime * ROTATION_SPEED * rotationFactor);
		tile.rotation %= 360.0F;
		
		float animation = tile.animation;
		if (animation > ANIMATION_DIVIDER) {
			animation = 1.0F;
		} else {
			animation /= ANIMATION_DIVIDER;
		}
		
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

		GlStateManager.translate(0.5F, 0.5F, 0.5F);

		texManager.bindTexture(Properties.RESOURCE_TEXURE_ANIMATIONS);
		MODEL_ANIMATION.render(1.0F / 16.0F, tile.rotation, animation);

		GlStateManager.popMatrix();
	}
}