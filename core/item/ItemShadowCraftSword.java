package shadowcraft.core.item;

import net.minecraft.src.EnumRarity;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ItemSword;

public class ItemShadowCraftSword extends ItemSword{

	public ItemShadowCraftSword(int itemID, EnumToolMaterial toolMaterial) {
		super(itemID, toolMaterial);
	}
	
	@Override
    public EnumRarity getRarity(ItemStack itemStack)
    {
        return EnumRarity.rare;
    }

}
