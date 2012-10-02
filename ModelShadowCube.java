package shadowcraft;

import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelShadowCube extends ModelBase
{
    /** The chest lid in the chest's model. */
    public ModelRenderer shadowCube = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 64);

    public ModelShadowCube(){
        shadowCube.addBox(0.0F, 0.0F, 0.0F, 14, 10, 14, 0.0F);
    }

    public void render(){
        shadowCube.render(0.0625F);
    }
}
