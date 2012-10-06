package shadowcraft.shadow.block;

import java.util.Random;

import shadowcraft.ShadowCraftShadow;

import net.minecraft.src.BlockOre;
import net.minecraft.src.CreativeTabs;

public class BlockDarkOre extends BlockOre{

	public BlockDarkOre(int blockID) {
		super(blockID, 5);
		blockIndexInTexture = 16 + 5;
		setBlockName("darkOre");
		setCreativeTab(CreativeTabs.tabBlock);
	}

	@Override
	public String getTextureFile(){
		return "/gui/scblocktex.png";
	}
	
	@Override
	public int idDropped(int i, Random random, int j)
	{
		return ShadowCraftShadow.shadowCrystal.shiftedIndex;
	}
	
	@Override
	public int quantityDropped(Random random){
		return random.nextInt(5) + 1;
	}
}
