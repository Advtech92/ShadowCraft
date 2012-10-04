package shadowcraft;

import org.lwjgl.opengl.GL11;

import buildcraft.api.liquids.ILiquidTank;
import buildcraft.api.liquids.LiquidManager;
import buildcraft.core.DefaultProps;

import net.minecraft.src.Block;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.StatCollector;
import net.minecraftforge.client.ForgeHooksClient;

public class GuiShadowRefinery extends GuiContainer {

	public TileEntityShadowRefinery shadowRefinery;
	
	public GuiShadowRefinery (InventoryPlayer inventoryPlayer, TileEntityShadowRefinery tileEntity) {
		super(new ContainerRefinery(inventoryPlayer, tileEntity));
		shadowRefinery = tileEntity;
	}

	@Override
	protected void drawGuiContainerForegroundLayer() {
		fontRenderer.drawString("Shadow Refinery", 8, 6, 0xff210752);
		fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 0xff210752);
		ILiquidTank tank = shadowRefinery.tank;
		int liquid;
		if(tank.getLiquid() == null){
			liquid = 0;
		}
		else{
			liquid = tank.getLiquid().amount;
		}
		fontRenderer.drawString(Integer.toString((int) liquid / LiquidManager.BUCKET_VOLUME), 8, 16, 0xff808080);
		displayGauge(10, 10, 3, 52, (int) ((liquid * 58) / 10000), ShadowCraft.liquidShadowMovingID);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		int texture = mc.renderEngine.getTexture("/gui/shadowrefinery.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(texture);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		this.drawTexturedModalRect(218, guiTop + 33, xSize + 1, 2, shadowRefinery.guiProgress, 16);
	}
	
	private void displayGauge(int j, int k, int line, int col, int squaled, int liquidId) {
		int liquidImgIndex = 0;

		if (liquidId < Block.blocksList.length && Block.blocksList[liquidId] != null) {
			ForgeHooksClient.bindTexture(Block.blocksList[liquidId].getTextureFile(), 0);
			liquidImgIndex = Block.blocksList[liquidId].blockIndexInTexture;
		} else if (Item.itemsList[liquidId] != null) {
			ForgeHooksClient.bindTexture(Item.itemsList[liquidId].getTextureFile(), 0);
			liquidImgIndex = Item.itemsList[liquidId].getIconFromDamage(0);
		} else {
			return;			
		}

		int imgLine = liquidImgIndex / 16;
		int imgColumn = liquidImgIndex - imgLine * 16;

		int start = 0;

		while (true) {
			int x = 0;

			if (squaled > 16) {
				x = 16;
				squaled -= 16;
			} else {
				x = squaled;
				squaled = 0;
			}

			drawTexturedModalRect(j + col, k + line + 58 - x - start, imgColumn * 26, imgLine * 16 + (16 - x), 28, 16 - (16 - x));
			start = start + 16;

			if (x == 0 || squaled == 0)
				break;
		}

		int i = mc.renderEngine.getTexture(DefaultProps.TEXTURE_PATH_GUI + "/combustion_engine_gui.png");

		mc.renderEngine.bindTexture(i);
		drawTexturedModalRect(j + col, k + line, 176, 0, 16, 60);
	}

}