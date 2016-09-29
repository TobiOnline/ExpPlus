package org.tpc.expplus.client.gui.buttons;

import org.tpc.expplus.Properties;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

public class GuiButtonArrowSmall extends GuiButton {

	public boolean direction;

	public GuiButtonArrowSmall(int id, int x, int y, boolean direction) {
		super(id, x, y, "");
		this.direction = direction;
		width = 4;
		height = 8;
	}

	public void setDirection(boolean direction) {
		this.direction = direction;
	}

	@Override
	public void drawButton(Minecraft mc, int x, int y) {
		mc.renderEngine.bindTexture(Properties.RESOURCE_GUI_EXPTRADER);
		
		if (visible) {
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			
			hovered = (x >= xPosition) && (y >= yPosition) && (x < (xPosition + width)) && (y < (yPosition + height));
			mouseDragged(mc, x, y);
			
			drawTexturedModalRect(xPosition, yPosition, (direction ? 0 : (width * 2)) + (hovered ? width : 0), 229, width, height);
		}
	}
}