package shadowcraft.shadow;

import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelShadowCube extends ModelBase
{
    public ModelRenderer sc;

    public ModelShadowCube(){
    	sc = new ModelRenderer(this, 0, 0);
    	sc.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, 0);
    }

    public void render(float i){
        sc.render(i);
    }
}
