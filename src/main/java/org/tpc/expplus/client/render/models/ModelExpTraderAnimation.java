package org.tpc.expplus.client.render.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelExpTraderAnimation extends ModelBase {
	
	public ModelRenderer pipe;
	public ModelRenderer goldBlock;

	public ModelExpTraderAnimation() {
		textureWidth = 64;
		textureHeight = 64;
		
		pipe = new ModelRenderer(this, 0, 12);
		pipe.addBox(-2.0F, 1.0F, -2.0F, 4, 4, 4);

		goldBlock = new ModelRenderer(this, 0, 0);
		goldBlock.addBox(-8.0F, 0.0F, -3.0F, 6, 6, 6);
		goldBlock.addBox(2.0F, 0.F, -3.0F, 6, 6, 6);
	}

	public void render(float scale) {
		pipe.render(scale);
		goldBlock.render(scale);
	}
}