package shadowcraft.core.item;

import net.minecraft.src.Item;

public class ItemShadowCraft extends Item{

	public ItemShadowCraft(final int itemID){
		super(itemID);
	}

	@Override
	public String getTextureFile(){
		return "/gui/scitemtex.png";
	}

}
