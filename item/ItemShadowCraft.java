package shadowcraft.item;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;

public class ItemShadowCraft extends Item{

	public ItemShadowCraft(int itemID) {
		super(itemID);
	}
	
	@Override
	public String getTextureFile(){
		return "/gui/scitemtex.png";
	}
	
	

}
