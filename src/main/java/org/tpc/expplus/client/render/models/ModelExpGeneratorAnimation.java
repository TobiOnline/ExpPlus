package org.tpc.expplus.client.render.models;

import java.util.BitSet;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;

import static org.tpc.expplus.ExpPlus.DEBUG;

public class ModelExpGeneratorAnimation extends ModelBase {

	private static final float ANGLE_PARTS = (float) Math.toRadians(90.0D);
	private static final float ANGLE_CONNECTIONS = (float) Math.toRadians(90.0D);
	
	public ModelRenderer staticPart;
	public ModelRenderer dynamicPart;

	public ModelRenderer connection;

	public BitSet connections;

	public ModelExpGeneratorAnimation() {
		textureWidth = 64;
		textureHeight = 64;

		connections = new BitSet(4);
		connections.set(0, 4);

		staticPart = new ModelRenderer(this, 0, 20);
		staticPart.addBox(-7.0F, 0.0F, -1.0F, 1, 5, 2);
		
//		dynamicPart = new ModelRenderer(this, 0, 20);
//		dynamicPart.addBox(-7.0F, 0.0F, -1.0F, 1, 5, 2);
//		dynamicPart.setRotationPoint(-8.0f, 10.0f, -1.0f);

		connection = new ModelRenderer(this, 0, 20);
		connection.addBox(-8.0f, 0.0f, -4.0f, 1, 4, 8);
	}

	public void render(float scale, float rotation, float animation) {
		if ((animation > 0.0F) && (animation < 1.0F)) {
			DEBUG("Generator Animation: " + animation);
		}
		
		for (int side = 0; side < 4; side++) {
			if (!connections.get(side))
				continue;
			
			connection.rotateAngleY = ANGLE_CONNECTIONS * side;
			connection.render(scale);
		}
		
		GlStateManager.rotate(rotation, 0.0F, 1.0F, 0.0F);
		for (int side = 0; side < 4; side++) {
			staticPart.rotateAngleY = ANGLE_PARTS * side;
			staticPart.render(scale);
		}
	}

	public void setConnections(BitSet connections) {
		this.connections = connections;
	}
	
	public void setConnection(int connection, boolean value) {
		connections.set(connection, value);
	}
}