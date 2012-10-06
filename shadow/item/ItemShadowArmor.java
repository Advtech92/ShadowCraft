package shadowcraft.shadow.item;

import shadowcraft.ShadowCraftShadow;
import net.minecraft.src.EnumArmorMaterial;
import net.minecraft.src.ItemArmor;
import net.minecraft.src.ItemStack;
import net.minecraftforge.common.IArmorTextureProvider;

public class ItemShadowArmor extends ItemArmor implements IArmorTextureProvider{

	public ItemShadowArmor(int itemID, EnumArmorMaterial armorMaterial, int texID, int partID) {
		super(itemID, armorMaterial, texID, partID);
	}
	
    @Override
    public String getTextureFile()
    {
            return "/gui/scitemtex.png";
    }

    @Override
	public String getArmorTextureFile(ItemStack itemStack){
		if(itemStack.itemID == ShadowCraftShadow.shadowHelmet.shiftedIndex || itemStack.itemID == ShadowCraftShadow.shadowChestplate.shiftedIndex ||
				itemStack.itemID == ShadowCraftShadow.shadowBoots.shiftedIndex){
			return "/armor/shadow_1.png";
		}
		if(itemStack.itemID == ShadowCraftShadow.shadowLeggings.shiftedIndex){
			return "/armor/shadow_2.png";
		}
		return "/armor/shadow_1.png";
	}
	
}
