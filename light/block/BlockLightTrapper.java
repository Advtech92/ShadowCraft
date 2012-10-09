package shadowcraft.light.block;

import shadowcraft.light.tileentity.TileEntityLightTrapper;
import net.minecraft.src.BlockContainer;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class BlockLightTrapper extends BlockContainer{

	public BlockLightTrapper(int blockID) {
		super(blockID, Material.rock);
		blockIndexInTexture = (16 * 2) + 5;
		setBlockName("lightTrapper");
		setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	@Override
	public String getTextureFile(){
		return "/gui/scblocktex.png";
	}
	
	@Override
	public int getBlockTextureFromSide(int side){
		switch(side){
			case 1:
				return (16 * 2) + 6;
		}
		return blockIndexInTexture;
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityLightTrapper();
	}
	
}
