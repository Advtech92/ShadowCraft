package shadowcraft;

import java.util.Random;

import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGeneratorShadowCraft implements IWorldGenerator{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider){
		switch(world.provider.dimensionId) {
			case -1:
				generateNether();
				break;
			case 0:
				generateSurface(world, random, chunkX*16, chunkZ*16);
				break;
            case 1:
            	generateEnd();
            	break;
		}
	}
    
	public void generateSurface(World world, Random rand, int chunkX, int chunkZ) {
		for (int i = 0; i < 30; i++) {
			int randPosX = chunkX + rand.nextInt(16);
			int randPosY = rand.nextInt(20);
			int randPosZ = chunkZ + rand.nextInt(16);
			
			(new WorldGenMinable(ShadowCraft.darkOreID, 4)).generate(world, rand, randPosX, randPosY, randPosZ);
		}
    }
	
	public void generateNether(){}
    
	public void generateEnd(){}
	
}
