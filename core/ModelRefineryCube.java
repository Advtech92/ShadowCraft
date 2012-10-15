package shadowcraft.core;

import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelRefineryCube extends ModelBase{
	public ModelRenderer cube;

	public ModelRefineryCube(int x, int y){
		cube = new ModelRenderer(this, x, y).setTextureSize(64, 64);
		cube.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, 0);
	}

	public void setTextureOffsets(final int x, final int y){
		cube.setTextureOffset(x, y);
	}

	public void render(final float f){
		cube.render(f);
	}
}
