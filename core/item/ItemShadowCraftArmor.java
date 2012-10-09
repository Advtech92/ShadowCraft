package shadowcraft.core.item;

import shadowcraft.ShadowCraftLight;
import shadowcraft.ShadowCraftShadow;
import net.minecraft.src.EnumArmorMaterial;
import net.minecraft.src.ItemArmor;
import net.minecraft.src.ItemStack;
import net.minecraftforge.common.IArmorTextureProvider;

public class ItemShadowCraftArmor extends ItemArmor implements IArmorTextureProvider{

	public String[] armorTextures = new String[2];
	public ItemShadowCraftArmor(int itemID, EnumArmorMaterial armorMaterial, int texID, int partID, String tex1, String tex2){
		super(itemID, armorMaterial, texID, partID);
		armorTextures[0] = tex1;
		armorTextures[1] = tex2;
	}
	
    @Override
    public String getTextureFile()
    {
            return "/gui/scitemtex.png";
    }

    @Override
	public String getArmorTextureFile(ItemStack itemStack){
		if(itemStack.itemID == ShadowCraftShadow.shadowLeggings.shiftedIndex ||
				itemStack.itemID == ShadowCraftLight.lightLeggings.shiftedIndex){
			return armorTextures[1];
		}
		return armorTextures[0];
	}
	
}
