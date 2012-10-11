package shadowcraft.shadow;

import net.minecraft.src.TileEntity;
import net.minecraft.src.TileEntitySpecialRenderer;
import net.minecraftforge.client.ForgeHooksClient;

import org.lwjgl.opengl.GL11;

import shadowcraft.core.ModelRefineryCube;
import shadowcraft.shadow.tileentity.TileEntityShadowRefinery;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderShadowCube extends TileEntitySpecialRenderer{

	private final ModelRefineryCube modelRefineryCube;

	public RenderShadowCube(final ModelRefineryCube modelRefineryCubeArg){
		modelRefineryCube = modelRefineryCubeArg;
	}

	public void renderShadowCubeAt(final TileEntityShadowRefinery tileEntity, final double x, final double y,
		final double z, final float f){
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1F, (float) z + 0.5F);
		ForgeHooksClient.bindTexture("/item/refinerycube.png", 0);
		GL11.glRotatef(tileEntity.cubeRotation, 1.0F, 1.0F, 1.0F);
		this.modelRefineryCube.render(0.03F);
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(final TileEntity tileEntity, final double x, final double y, final double z,
		final float f){
		this.renderShadowCubeAt((TileEntityShadowRefinery) tileEntity, x, y, z, f);
	}

}
