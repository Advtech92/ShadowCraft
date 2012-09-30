package shadowcraft.item;

import java.awt.List;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import net.minecraft.src.EnumRarity;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.ItemPickaxe;
import net.minecraft.src.ItemStack;

public class ShadowPickaxe extends ItemPickaxe{
	
	public ShadowPickaxe(int itemID, EnumToolMaterial toolMaterial) {
		super(itemID, toolMaterial);
	}
	
	@Override
    public EnumRarity getRarity(ItemStack itemStack)
    {
        return EnumRarity.rare;
    }
	
}
