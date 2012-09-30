package shadowcraft;

import org.lwjgl.opengl.GL11;

import net.minecraft.src.GuiContainer;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.StatCollector;

public class GuiShadowRefinery extends GuiContainer {

	public GuiShadowRefinery (InventoryPlayer inventoryPlayer, TEShadowRefinery tileEntity) {
		super(new ContainerRefinery(inventoryPlayer, tileEntity));
	}

	@Override
	protected void drawGuiContainerForegroundLayer() {
		fontRenderer.drawString("Shadow Refinery", 8, 6, 0xff210752);
		fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 0xff210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		int texture = mc.renderEngine.getTexture("/gui/shadowrefinery.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(texture);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}

}