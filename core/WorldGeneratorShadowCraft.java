package shadowcraft.core;

import java.util.Random;

import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenMinable;
import shadowcraft.ShadowCraftShadow;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGeneratorShadowCraft implements IWorldGenerator{

	@Override
	public void generate(final Random random, final int chunkX, final int chunkZ, final World world,
		final IChunkProvider chunkGenerator, final IChunkProvider chunkProvider){
		switch (world.provider.dimensionId) {
		case -1:
			generateNether();
			break;
		case 0:
			generateSurface(world, random, chunkX * 16, chunkZ * 16);
			break;
		case 1:
			generateEnd();
			break;
		}
	}

	public void generateSurface(final World world, final Random rand, final int chunkX, final int chunkZ){
		for (int i = 0; i < 30; i++) {
			final int randPosX = chunkX + rand.nextInt(16);
			final int randPosY = rand.nextInt(20);
			final int randPosZ = chunkZ + rand.nextInt(16);

			(new WorldGenMinable(ShadowCraftShadow.darkOreID, 4)).generate(world, rand, randPosX, randPosY, randPosZ);
		}
	}

	public void generateNether(){}

	public void generateEnd(){}

}
