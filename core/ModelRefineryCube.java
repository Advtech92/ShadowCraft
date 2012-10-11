package shadowcraft.core;

import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelRefineryCube extends ModelBase
{
    public ModelRenderer cube;

    public ModelRefineryCube(){
    	cube = new ModelRenderer(this, 0, 0).setTextureSize(64, 64);
    	cube.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, 0);
    }

    public void setTextureOffsets(int x, int y){
    	cube.setTextureOffset(x, y);
    }
    
    public void render(float f){
        cube.render(f);
    }
}
