package shadowcraft.shadow.block;

import java.util.Random;

import net.minecraft.src.BlockContainer;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import shadowcraft.shadow.tileentity.TileEntityShadowCatcher;

public class BlockShadowCatcher extends BlockContainer{

	private final String[] list = { "smoke", "flame", "lava" };

	public BlockShadowCatcher(final int par1){
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
	public void randomDisplayTick(final World world, final int x, final int y, final int z, final Random par5Random){
		final int lightLevel = world.getBlockLightValue(x, y + 1, z);
		final boolean isCheatyBlockAbove = !world.isAirBlock(x, y + 1, z);
		if ((lightLevel < 3) && (!isCheatyBlockAbove)) {
			final Random r = new Random();
			final float var7 = x + 0.5F;
			final float var8 = y + 0.0F + ((par5Random.nextFloat() * 6.0F) / 16.0F);
			final float var9 = z + 0.5F;
			final float var10 = 0.52F;
			final float var11 = (par5Random.nextFloat() * 0.6F) - 0.3F;
			world.spawnParticle(list[r.nextInt(list.length)], var7 - var10, var8, var9 + var11, 0.0D, 0.0D, 0.0D);
			world.spawnParticle(list[r.nextInt(list.length)], var7 + var10, var8, var9 + var11, 0.0D, 0.0D, 0.0D);
			world.spawnParticle(list[r.nextInt(list.length)], var7 + var11, var8, var9 - var10, 0.0D, 0.0D, 0.0D);
			world.spawnParticle(list[r.nextInt(list.length)], var7 + var11, var8, var9 + var10, 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	public int getBlockTextureFromSide(final int side){
		switch (side) {
		case 1:
			return 6;
		}
		return blockIndexInTexture;
	}

	@Override
	public TileEntity createNewTileEntity(final World var1){
		return new TileEntityShadowCatcher();
	}

}
