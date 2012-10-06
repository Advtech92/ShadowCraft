package shadowcraft.shadow.item;

import net.minecraft.src.EnumRarity;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ItemSword;

public class ItemShadowSword extends ItemSword{

	public ItemShadowSword(int itemID, EnumToolMaterial toolMaterial) {
		super(itemID, toolMaterial);
	}
	
	@Override
    public EnumRarity getRarity(ItemStack itemStack)
    {
        return EnumRarity.rare;
    }

}
