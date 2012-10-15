package shadowcraft.shadow;

import net.minecraft.src.Block;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.StatCollector;
import net.minecraftforge.client.ForgeHooksClient;

import org.lwjgl.opengl.GL11;

import shadowcraft.ShadowCraftShadow;
import shadowcraft.shadow.tileentity.TileEntityShadowRefinery;
import buildcraft.api.liquids.ILiquidTank;
import buildcraft.api.liquids.LiquidManager;
import buildcraft.core.DefaultProps;

public class GuiShadowRefinery extends GuiContainer{

	public TileEntityShadowRefinery shadowRefinery;

	public GuiShadowRefinery(final InventoryPlayer inventoryPlayer, final TileEntityShadowRefinery tileEntity){
		super(new ContainerShadowRefinery(inventoryPlayer, tileEntity));
		shadowRefinery = tileEntity;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(){
		fontRenderer.drawString("Shadow Refinery", 8, 6, 4210752);
		fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, (ySize - 96) + 2, 4210752);
		final ILiquidTank tank = shadowRefinery.tank;
		int liquid;
		if (tank.getLiquid() == null) {
			liquid = 0;
		} else {
			liquid = tank.getLiquid().amount;
		}
		displayGauge(10, 10, 3, 52, (liquid * 58) / 10000, ShadowCraftShadow.liquidShadowMovingID);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(final float par1, final int par2, final int par3){
		final int texture = mc.renderEngine.getTexture("/gui/shadowrefinery.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(texture);
		final int x = (width - xSize) / 2;
		final int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		this.drawTexturedModalRect(218, guiTop + 33, xSize + 1, 2, shadowRefinery.guiProgress, 16);
	}

	private void displayGauge(final int j, final int k, final int line, final int col, int squaled, final int liquidId){
		int liquidImgIndex = 0;

		if ((liquidId < Block.blocksList.length) && (Block.blocksList[liquidId] != null)) {
			ForgeHooksClient.bindTexture(Block.blocksList[liquidId].getTextureFile(), 0);
			liquidImgIndex = Block.blocksList[liquidId].blockIndexInTexture;
		} else if (Item.itemsList[liquidId] != null) {
			ForgeHooksClient.bindTexture(Item.itemsList[liquidId].getTextureFile(), 0);
			liquidImgIndex = Item.itemsList[liquidId].getIconFromDamage(0);
		} else {
			return;
		}

		final int imgLine = liquidImgIndex / 16;
		final int imgColumn = liquidImgIndex - (imgLine * 16);

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

			drawTexturedModalRect(j + col, (k + line + 58) - x - start, imgColumn * 26, (imgLine * 16) + (16 - x), 28,
				16 - (16 - x));
			start = start + 16;

			if ((x == 0) || (squaled == 0)) {
				break;
			}
		}

		final int i = mc.renderEngine.getTexture(DefaultProps.TEXTURE_PATH_GUI + "/combustion_engine_gui.png");

		mc.renderEngine.bindTexture(i);
		drawTexturedModalRect(j + col, k + line, 176, 0, 16, 60);
	}

}