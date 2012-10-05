package shadowcraft.block;

import java.util.Random;

import shadowcraft.ShadowCraft;

import net.minecraft.src.Block;
import net.minecraft.src.BlockOre;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;
import net.minecraft.src.Material;

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
		return ShadowCraft.shadowCrystal.shiftedIndex;
	}
	
	@Override
	public int quantityDropped(Random random){
		return random.nextInt(5) + 1;
	}
}
