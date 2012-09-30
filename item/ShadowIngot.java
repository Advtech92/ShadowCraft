package shadowcraft.item;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;

public class ShadowIngot extends Item{

	public ShadowIngot(int itemID) {
		super(itemID);
		this.setCreativeTab(CreativeTabs.tabMaterials);
	}

    @Override
    public String getTextureFile()
    {
            return "/gui/scitemtex.png";
    }
    
}
