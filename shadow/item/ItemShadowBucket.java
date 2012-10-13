package shadowcraft.shadow.item;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumMovingObjectType;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.World;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import shadowcraft.ShadowCraftShadow;

public class ItemShadowBucket extends Item{
	/** field for checking if the bucket has been filled. */
	private final int isFull;

	public ItemShadowBucket(final int par1, final int par2){
		super(par1);
		this.maxStackSize = 1;
		this.isFull = par2;
		this.setCreativeTab(CreativeTabs.tabMisc);
	}

	@Override
	public String getTextureFile(){
		return "/gui/scitemtex.png";
	}
}
