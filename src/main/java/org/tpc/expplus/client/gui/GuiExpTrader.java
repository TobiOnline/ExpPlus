package org.tpc.expplus.client.gui;

import java.io.IOException;

import org.tpc.expplus.Properties;
import org.tpc.expplus.client.gui.buttons.GuiButtonArrowBig;
import org.tpc.expplus.client.gui.buttons.GuiButtonArrowSmall;
import org.tpc.expplus.common.inventory.container.ContainerExpTrader;
import org.tpc.expplus.common.tile.TileExpTrader;
import org.tpc.expplus.util.ExpUtil;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;

import static org.tpc.expplus.ExpPlus.DEBUG;

public class GuiExpTrader extends GuiContainer {

	public ContainerExpTrader container;

	public GuiButtonArrowBig craftingArrow;
	public GuiButton tradeButton;

	public GuiExpTrader(ContainerExpTrader container) {
		super(container);
		this.container = container;
		
		xSize = 190;
		ySize = 204;
	}

	@Override
	public void initGui() {
		super.initGui();
		
		tradeButton = new GuiButton(0, guiLeft + 114, guiTop + 67, 63, 20, "Trade");
		buttonList.add(tradeButton);

		TileExpTrader trader = container.getTrader();
		if (trader.isOwner(container.getPlayer())) {
			buttonList.add(new GuiButtonArrowSmall(1, guiLeft + 14, guiTop + 35, false));
			buttonList.add(new GuiButtonArrowSmall(2, guiLeft + 71, guiTop + 35, true));
			buttonList.add(new GuiButtonArrowSmall(3, guiLeft + 14, guiTop + 44, false));
			buttonList.add(new GuiButtonArrowSmall(4, guiLeft + 71, guiTop + 44, true));

			buttonList.add(new GuiButtonArrowSmall(5, guiLeft + 14, guiTop + 73, false));
			buttonList.add(new GuiButtonArrowSmall(6, guiLeft + 71, guiTop + 73, true));
			buttonList.add(new GuiButtonArrowSmall(7, guiLeft + 14, guiTop + 82, false));
			buttonList.add(new GuiButtonArrowSmall(8, guiLeft + 71, guiTop + 82, true));
		}

		craftingArrow = new GuiButtonArrowBig(9, guiLeft + 84, guiTop + 30, trader.getDirection());
		buttonList.add(craftingArrow);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		TileExpTrader trader = container.getTrader();
		
		GlStateManager.color(1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(Properties.RESOURCE_GUI_EXPTRADER);

		drawXpBar(14, 26, ExpUtil.getExperienceBarValue(trader.getTradeExperience()));
		drawXpBar(14, 64, ExpUtil.getExperienceBarValue(trader.getOwnerExperience()));
		
		fontRendererObj.drawStringWithShadow("\2476Lvl", 20, 35, -1);
		fontRendererObj.drawStringWithShadow("\247e" + ExpUtil.getExperienceLevel(trader.getTradeExperience()), 39, 35, -1);
		fontRendererObj.drawStringWithShadow("\2476Exp", 20, 44, -1);
		fontRendererObj.drawStringWithShadow("\247e" + trader.getTradeExperience(), 39, 44, -1);

		fontRendererObj.drawStringWithShadow("\2476Lvl", 20, 73, -1);
		fontRendererObj.drawStringWithShadow("\247e" + ExpUtil.getExperienceLevel(trader.getOwnerExperience()), 39, 73, -1);
		fontRendererObj.drawStringWithShadow("\2476Exp", 20, 82, -1);
		fontRendererObj.drawStringWithShadow("\247e" + trader.getOwnerExperience(), 39, 82, -1);

		fontRendererObj.drawStringWithShadow("\2476Owner \247e" + trader.getOwnerName(), 20, 192, -1);
	}

	private void drawXpBar(int x, int y, float exp) {
		drawTexturedModalRect(x, y, 0, 204, 64, 5);
		drawTexturedModalRect(x, y, 0, 209, (int) (exp * 64.0F), 5);
	}

	@Override
	protected void keyTyped(char c, int id) {
		DEBUG("Key typed " + id + "(" + c + ")");
		switch(id) {
		case 18:
			keyTyped((char)1, 1);
			return;
		default:
			try {
				super.keyTyped(c, id);
			} catch (IOException e) { }
			return;
		}
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		DEBUG("Button pressed " + button.id);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		TileExpTrader trader = container.getTrader();
		
		GlStateManager.color(1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(Properties.RESOURCE_GUI_EXPTRADER);
		
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

		if (!trader.isOwner(container.getPlayer())) {
			drawTexturedModalRect(guiLeft + 149, guiTop + 23, 226, 194, 30, 31);
			drawTexturedModalRect(guiLeft + 11, guiTop + 61, 189, 225, 67, 31);
		}
	}
}