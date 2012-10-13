package shadowcraft.core.block;

import java.util.Random;

import net.minecraft.src.BlockOre;
import net.minecraft.src.CreativeTabs;
import shadowcraft.ShadowCraftShadow;

public class BlockOreShadowCraft extends BlockOre{

	public int idDropped;
	
	public BlockOreShadowCraft(final int blockID, int idDroppedArg, int texIndex){
		super(blockID, 5);
		blockIndexInTexture = texIndex;
		setBlockName("darkOre");
		setCreativeTab(CreativeTabs.tabBlock);
		idDropped = idDroppedArg;
	}

	@Override
	public String getTextureFile(){
		return "/gui/scblocktex.png";
	}

	@Override
	public int idDropped(final int i, final Random random, final int j){
		return idDropped;
	}

	@Override
	public int quantityDropped(final Random random){
		return random.nextInt(5) + 1;
	}
}
