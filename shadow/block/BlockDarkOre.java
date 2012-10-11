package shadowcraft.shadow.block;

import java.util.Random;

import net.minecraft.src.BlockOre;
import net.minecraft.src.CreativeTabs;
import shadowcraft.ShadowCraftShadow;

public class BlockDarkOre extends BlockOre{

	public BlockDarkOre(final int blockID){
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
	public int idDropped(final int i, final Random random, final int j){
		return ShadowCraftShadow.shadowCrystal.shiftedIndex;
	}

	@Override
	public int quantityDropped(final Random random){
		return random.nextInt(5) + 1;
	}
}
