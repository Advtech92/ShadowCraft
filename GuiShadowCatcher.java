package shadowcraft;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import net.minecraft.src.GuiContainer;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.StatCollector;

public class GuiShadowCatcher extends GuiContainer {

	private TileEntityShadowCatcher shadowCatcher;
	private int teShadows;
	public GuiShadowCatcher(InventoryPlayer playerInventory, TileEntityShadowCatcher tileEntity, int shad) {
		shadowCatcher = tileEntity;
		System.out.print("GuiShadowCatcher init");
		System.out.print(", shadows (?): ");
		System.out.print(tileEntity.getShadows());
		System.out.println();
	}

	@SideOnly(Side.CLIENT)
	protected void drawGuiContainerForegroundLayer() {
		//System.out.println(this.shadowCatcher.getShadows());
		teShadows = shadowCatcher.getShadows();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		fontRenderer.drawString("Shadow", 45, 6, 0xff373737);
		fontRenderer.drawString("Catcher", 95, 6, 0xff373737);
		/*fontRenderer.drawString(Integer.toString(this.shadowCatcher.lightLevel), 150, 6, 0xff373737);
		 */
		int x = 89 - (fontRenderer.getStringWidth(Integer.toString(teShadows)) / 2);
		fontRenderer.drawString(Integer.toString(teShadows), x, 56, 0xff373737);
		/*
		fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 94, 0xff373737);*/
		/*
		drawRect(88, 16, 88 + 38, 24, 0xff373737);
		drawRect(89, 17, 88 + 37, 23, 0xff969696);
		drawRect(88 + 30, 23, 88 + 38, 45, 0xff373737);
		drawRect(88 + 31, 23, 88 + 37, 44, 0xff969696);
		drawRect(88 + 31, 45, 88 + 8, 37, 0xff373737);
		drawRect(88 + 31, 44, 88 + 8, 38, 0xff969696);
		drawRect(89, 17, 88 + ((((shadows * 2)/270) > 37) ? 37 : (int) ((shadows * 2)/270)), 23, 0xff000000);
		if(shadows > 5000){
			int i = (((shadows - 5000) * 2) / 238);
			drawRect(88 + 31, 23, 88 + 37, 23 + ((i > 21) ? 21 : (int) ((shadows - 5000) * 2) / 238), 0xff000000);
			if(shadows > 7500){
				int i2 = (shadows - 7500) / 108;
				drawRect(88 + 31, 44, (88 + 31) - i2, 38, 0xff000000);
			}
		}
		*/
	}
	
	@SideOnly(Side.CLIENT)
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		int var4 = mc.renderEngine.getTexture("/gui/shadowcatcher.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(var4);
        int var5 = guiLeft;
        int var6 = guiTop;
        drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);	
        //drawTexturedModalRect(203, 41, this.xSize, 0, 20, (int) this.shadowCatcher.publicShadows / 384); // x : 20, y : 26
	}

}
