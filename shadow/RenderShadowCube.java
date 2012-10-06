package shadowcraft.shadow;

import org.lwjgl.opengl.GL11;

import shadowcraft.shadow.tileentity.TileEntityShadowRefinery;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import net.minecraft.src.TileEntity;
import net.minecraft.src.TileEntitySpecialRenderer;
import net.minecraftforge.client.ForgeHooksClient;

@SideOnly(Side.CLIENT)
public class RenderShadowCube extends TileEntitySpecialRenderer{
	
	private ModelShadowCube modelShadowCube;
	
	public RenderShadowCube(ModelShadowCube modelShadowCubeArg){
		modelShadowCube = modelShadowCubeArg;
	}
	
	public void renderShadowCubeAt(TileEntityShadowRefinery tileEntity, double x, double y, double z, float f){
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x + 0.35F, (float)y + 1F, (float)z + 0.35F);
		ForgeHooksClient.bindTexture("/item/shadowcube.png", 0);
		GL11.glRotatef(tileEntity.cubeRotation, 1.0F, 1.0F, 1.0F);
		this.modelShadowCube.render();
		GL11.glPopMatrix();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f) {
		this.renderShadowCubeAt((TileEntityShadowRefinery) tileEntity, x, y, z, f);
	}
	
	
}
