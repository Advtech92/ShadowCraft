package shadowcraft.core.item;

import net.minecraft.src.EnumRarity;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.ItemAxe;
import net.minecraft.src.ItemStack;

public class ItemShadowCraftAxe extends ItemAxe{

	public ItemShadowCraftAxe(final int itemID, final EnumToolMaterial toolMaterial){
		super(itemID, toolMaterial);
	}

	@Override
	public EnumRarity getRarity(final ItemStack itemStack){
		return EnumRarity.rare;
	}

	@Override
	public String getTextureFile(){
		return "/gui/scitemtex.png";
	}

}
