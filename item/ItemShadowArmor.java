package shadowcraft.item;

import shadowcraft.ShadowCraft;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumArmorMaterial;
import net.minecraft.src.ItemArmor;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
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
		if(itemStack.itemID == ShadowCraft.shadowHelmet.shiftedIndex || itemStack.itemID == ShadowCraft.shadowChestplate.shiftedIndex ||
				itemStack.itemID == ShadowCraft.shadowBoots.shiftedIndex){
			return "/armor/shadow_1.png";
		}
		if(itemStack.itemID == ShadowCraft.shadowLeggings.shiftedIndex){
			return "/armor/shadow_2.png";
		}
		return "/armor/shadow_1.png";
	}
	
}
