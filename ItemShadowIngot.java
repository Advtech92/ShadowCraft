package shadowcraft;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;

public class ItemShadowIngot extends Item{

	public ItemShadowIngot(int itemID) {
		super(itemID);
		this.setCreativeTab(CreativeTabs.tabMaterials);
	}

    @Override
    public String getTextureFile()
    {
            return "/gui/scitemtex.png";
    }
    
}
