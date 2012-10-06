package shadowcraft.shadow.block;

import java.util.Random;

import shadowcraft.shadow.tileentity.TileEntityShadowCatcher;

import net.minecraft.src.BlockContainer;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class BlockShadowCatcher extends BlockContainer{

	private String[] list = {"smoke", "flame", "lava"};
	public BlockShadowCatcher(int par1) {
		super(par1, Material.rock);
		blockIndexInTexture = 5;
		setBlockName("shadowCatcher");
		setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	@Override
	public String getTextureFile(){
		return "/gui/scblocktex.png";
	}
	
	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random par5Random){
		int lightLevel = world.getBlockLightValue(x, y + 1, z);
		boolean isCheatyBlockAbove = !world.isAirBlock(x, y + 1, z);
		if((lightLevel < 3) && (!isCheatyBlockAbove)){
			Random r = new Random();
			float var7 = x + 0.5F;
			float var8 = y + 0.0F + par5Random.nextFloat() * 6.0F / 16.0F;
			float var9 = z + 0.5F;
			float var10 = 0.52F;
			float var11 = par5Random.nextFloat() * 0.6F - 0.3F;
			world.spawnParticle(list[r.nextInt(list.length)], var7 - var10, var8, var9 + var11, 0.0D, 0.0D, 0.0D);
			world.spawnParticle(list[r.nextInt(list.length)], var7 + var10, var8, var9 + var11, 0.0D, 0.0D, 0.0D);
			world.spawnParticle(list[r.nextInt(list.length)], var7 + var11, var8, var9 - var10, 0.0D, 0.0D, 0.0D);
			world.spawnParticle(list[r.nextInt(list.length)], var7 + var11, var8, var9 + var10, 0.0D, 0.0D, 0.0D);
		}
	}
	
	@Override
	public int getBlockTextureFromSide(int side){
		switch(side){
			case 1:
				return 6;
		}
		return blockIndexInTexture;
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityShadowCatcher();
	}


	
}
