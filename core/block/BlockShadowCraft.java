package shadowcraft.core.block;

import net.minecraft.src.Block;
import net.minecraft.src.Material;

public class BlockShadowCraft extends Block{

	public BlockShadowCraft(final int blockID, final Material material){
		super(blockID, material);
	}

	@Override
	public String getTextureFile(){
		return "/gui/scblocktex.png";
	}

}
