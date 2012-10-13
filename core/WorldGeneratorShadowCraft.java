package shadowcraft.core;

import java.util.Random;

import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenMinable;
import shadowcraft.ShadowCraftLight;
import shadowcraft.ShadowCraftShadow;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGeneratorShadowCraft implements IWorldGenerator{

	public WorldGenMinable darkOreGen = new WorldGenMinable(ShadowCraftShadow.darkOreID, 10);
	public WorldGenMinable brightOreGen = new WorldGenMinable(ShadowCraftLight.brightOreID, 10);
	
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
			generateOre(2, world, rand, chunkX, chunkZ, darkOreGen, 20);
			generateOre(2, world, rand, chunkX, chunkZ, brightOreGen, 20);
	}

	public void generateNether(){}

	public void generateEnd(){}
	
	public void generateOre(final int loops, World world, Random random, final int chunkX, final int chunkZ, WorldGenMinable gen, final int yLevel){
		for(int x = 0; x < loops; ++x){
			int randPosX = chunkX + random.nextInt(16);
			int randPosY = random.nextInt(yLevel);
			int randPosZ = chunkZ + random.nextInt(16);
			gen.generate(world, random, randPosX, randPosY, randPosZ);
		}
	}

}
