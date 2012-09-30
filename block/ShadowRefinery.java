package shadowcraft.block;

import net.minecraft.src.BlockContainer;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class ShadowRefinery extends BlockContainer{

	public ShadowRefinery(int itemID) {
		super(itemID, Material.rock);
		blockIndexInTexture = 8;
	}

	@Override
	public String getTextureFile(){
		return "/gui/scblocktex.png";
	}
	
	@Override
	public int getBlockTextureFromSide(int side){
		switch(side){
			case 0:
				return 10;
			case 1:
				return 9;
		}
		return blockIndexInTexture;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return null;
	}
	
	

}
