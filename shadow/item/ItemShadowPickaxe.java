package shadowcraft.shadow.item;

import net.minecraft.src.EnumRarity;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.ItemPickaxe;
import net.minecraft.src.ItemStack;

public class ItemShadowPickaxe extends ItemPickaxe{
	
	public ItemShadowPickaxe(int itemID, EnumToolMaterial toolMaterial) {
		super(itemID, toolMaterial);
	}
	
	@Override
    public EnumRarity getRarity(ItemStack itemStack)
    {
        return EnumRarity.rare;
    }
	
}
