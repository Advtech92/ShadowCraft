/*
 *** MADE BY MITHION'S .SCHEMATIC TO JAVA CONVERTING TOOL v1.6 ***
 */

package shadowcraft.core;

import java.util.Random;

import shadowcraft.ShadowCraftLight;
import shadowcraft.ShadowCraftShadow;

import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class StructureShadowCraftTemple extends WorldGenerator{
	protected int[] GetValidSpawnBlocks(){

		return new int[]{ Block.dirt.blockID, Block.sand.blockID, Block.grass.blockID, Block.ice.blockID };
	}

	public boolean LocationIsValidSpawn(World world, int i, int j, int k){
		int distanceToAir = 0;
		int checkID = world.getBlockId(i, j, k);

		while (checkID != 0){
			distanceToAir++;
			checkID = world.getBlockId(i, j + distanceToAir, k);
		}

		if (distanceToAir > 3){
			return false;
		}
		j += distanceToAir - 1;

		int blockID = world.getBlockId(i, j, k);
		int blockIDAbove = world.getBlockId(i, j + 1, k);
		int blockIDBelow = world.getBlockId(i, j - 1, k);
		for (int x : GetValidSpawnBlocks()){
			if (blockIDAbove != 0){
				return false;
			}
			if (blockID == x){
				return true;
			} else if (blockID == Block.snow.blockID && blockIDBelow == x){
				return true;
			}
		}
		return false;
	}

	public StructureShadowCraftTemple(){}

	public boolean generate(World world, Random rand, int i, int j, int k){
		if (!LocationIsValidSpawn(world, i, j, k) || !LocationIsValidSpawn(world, i + 6, j, k)
			|| !LocationIsValidSpawn(world, i + 6, j, k + 6) || !LocationIsValidSpawn(world, i, j, k + 6)){
			return false;
		}

		int themedCatcherID, themedRefineryID, themedBlockID;
		if(rand.nextBoolean() == true){
			themedCatcherID = ShadowCraftShadow.shadowCatcherID;
			themedRefineryID = ShadowCraftShadow.shadowRefineryID;
			themedBlockID = ShadowCraftShadow.shadowBlockID;
		}
		else{
			themedCatcherID = ShadowCraftLight.lightTrapperID;
			themedRefineryID = ShadowCraftLight.lightRefineryID;
			themedBlockID = ShadowCraftLight.lightBlockID;
		}
		
		world.setBlockAndMetadata(i + 0, j + 0, k + 0, Block.planks.blockID, 1);
		world.setBlockAndMetadata(i + 0, j + 0, k + 1, Block.planks.blockID, 1);
		world.setBlockAndMetadata(i + 0, j + 0, k + 2, Block.planks.blockID, 1);
		world.setBlock(i + 0, j + 0, k + 3, themedBlockID);
		world.setBlockAndMetadata(i + 0, j + 0, k + 4, Block.planks.blockID, 1);
		world.setBlockAndMetadata(i + 0, j + 0, k + 5, Block.planks.blockID, 1);
		world.setBlockAndMetadata(i + 0, j + 0, k + 6, Block.planks.blockID, 1);
		world.setBlock(i + 0, j + 1, k + 0, Block.fence.blockID);
		world.setBlock(i + 0, j + 1, k + 1, Block.fence.blockID);
		world.setBlock(i + 0, j + 1, k + 2, 0);
		world.setBlock(i + 0, j + 1, k + 3, 0);
		world.setBlock(i + 0, j + 1, k + 4, 0);
		world.setBlock(i + 0, j + 1, k + 5, Block.fence.blockID);
		world.setBlock(i + 0, j + 1, k + 6, Block.fence.blockID);
		world.setBlock(i + 0, j + 2, k + 0, Block.fence.blockID);
		world.setBlock(i + 0, j + 2, k + 1, 0);
		world.setBlock(i + 0, j + 2, k + 2, 0);
		world.setBlock(i + 0, j + 2, k + 3, 0);
		world.setBlock(i + 0, j + 2, k + 4, 0);
		world.setBlock(i + 0, j + 2, k + 5, 0);
		world.setBlock(i + 0, j + 2, k + 6, Block.fence.blockID);
		world.setBlock(i + 0, j + 3, k + 0, Block.fence.blockID);
		world.setBlock(i + 0, j + 3, k + 1, 0);
		world.setBlock(i + 0, j + 3, k + 2, 0);
		world.setBlock(i + 0, j + 3, k + 3, 0);
		world.setBlock(i + 0, j + 3, k + 4, 0);
		world.setBlock(i + 0, j + 3, k + 5, 0);
		world.setBlock(i + 0, j + 3, k + 6, Block.fence.blockID);
		world.setBlock(i + 0, j + 4, k + 0, Block.fence.blockID);
		world.setBlock(i + 0, j + 4, k + 1, 0);
		world.setBlock(i + 0, j + 4, k + 2, 0);
		world.setBlock(i + 0, j + 4, k + 3, 0);
		world.setBlock(i + 0, j + 4, k + 4, 0);
		world.setBlock(i + 0, j + 4, k + 5, 0);
		world.setBlock(i + 0, j + 4, k + 6, Block.fence.blockID);
		world.setBlockAndMetadata(i + 0, j + 5, k + 0, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 0, j + 5, k + 1, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 0, j + 5, k + 2, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 0, j + 5, k + 3, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 0, j + 5, k + 4, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 0, j + 5, k + 5, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 0, j + 5, k + 6, Block.woodSingleSlab.blockID, 2);
		world.setBlock(i + 0, j + 6, k + 0, 0);
		world.setBlock(i + 0, j + 6, k + 1, 0);
		world.setBlock(i + 0, j + 6, k + 2, 0);
		world.setBlock(i + 0, j + 6, k + 3, 0);
		world.setBlock(i + 0, j + 6, k + 4, 0);
		world.setBlock(i + 0, j + 6, k + 5, 0);
		world.setBlock(i + 0, j + 6, k + 6, 0);
		world.setBlockAndMetadata(i + 1, j + 0, k + 0, Block.planks.blockID, 1);
		world.setBlockAndMetadata(i + 1, j + 0, k + 1, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 1, j + 0, k + 2, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 1, j + 0, k + 3, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 1, j + 0, k + 4, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 1, j + 0, k + 5, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 1, j + 0, k + 6, Block.planks.blockID, 1);
		world.setBlock(i + 1, j + 1, k + 0, Block.fence.blockID);
		world.setBlock(i + 1, j + 1, k + 1, 0);
		world.setBlock(i + 1, j + 1, k + 2, 0);
		world.setBlock(i + 1, j + 1, k + 3, 0);
		world.setBlock(i + 1, j + 1, k + 4, 0);
		world.setBlock(i + 1, j + 1, k + 5, 0);
		world.setBlock(i + 1, j + 1, k + 6, Block.fence.blockID);
		world.setBlock(i + 1, j + 2, k + 0, 0);
		world.setBlock(i + 1, j + 2, k + 1, 0);
		world.setBlock(i + 1, j + 2, k + 2, 0);
		world.setBlock(i + 1, j + 2, k + 3, 0);
		world.setBlock(i + 1, j + 2, k + 4, 0);
		world.setBlock(i + 1, j + 2, k + 5, 0);
		world.setBlock(i + 1, j + 2, k + 6, 0);
		world.setBlock(i + 1, j + 3, k + 0, 0);
		world.setBlock(i + 1, j + 3, k + 1, 0);
		world.setBlock(i + 1, j + 3, k + 2, 0);
		world.setBlock(i + 1, j + 3, k + 3, 0);
		world.setBlock(i + 1, j + 3, k + 4, 0);
		world.setBlock(i + 1, j + 3, k + 5, 0);
		world.setBlock(i + 1, j + 3, k + 6, 0);
		world.setBlock(i + 1, j + 4, k + 0, 0);
		world.setBlock(i + 1, j + 4, k + 1, 0);
		world.setBlock(i + 1, j + 4, k + 2, 0);
		world.setBlock(i + 1, j + 4, k + 3, 0);
		world.setBlock(i + 1, j + 4, k + 4, 0);
		world.setBlock(i + 1, j + 4, k + 5, 0);
		world.setBlock(i + 1, j + 4, k + 6, 0);
		world.setBlockAndMetadata(i + 1, j + 5, k + 0, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 1, j + 5, k + 1, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 1, j + 5, k + 2, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 1, j + 5, k + 3, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 1, j + 5, k + 4, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 1, j + 5, k + 5, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 1, j + 5, k + 6, Block.woodSingleSlab.blockID, 2);
		world.setBlock(i + 1, j + 6, k + 0, 0);
		world.setBlock(i + 1, j + 6, k + 1, 0);
		world.setBlock(i + 1, j + 6, k + 2, 0);
		world.setBlock(i + 1, j + 6, k + 3, 0);
		world.setBlock(i + 1, j + 6, k + 4, 0);
		world.setBlock(i + 1, j + 6, k + 5, 0);
		world.setBlock(i + 1, j + 6, k + 6, 0);
		world.setBlockAndMetadata(i + 2, j + 0, k + 0, Block.planks.blockID, 1);
		world.setBlockAndMetadata(i + 2, j + 0, k + 1, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 2, j + 0, k + 2, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 2, j + 0, k + 3, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 2, j + 0, k + 4, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 2, j + 0, k + 5, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 2, j + 0, k + 6, Block.planks.blockID, 1);
		world.setBlock(i + 2, j + 1, k + 0, 0);
		world.setBlock(i + 2, j + 1, k + 1, 0);
		world.setBlock(i + 2, j + 1, k + 2, 0);
		world.setBlock(i + 2, j + 1, k + 3, 0);
		world.setBlock(i + 2, j + 1, k + 4, 0);
		world.setBlock(i + 2, j + 1, k + 5, 0);
		world.setBlock(i + 2, j + 1, k + 6, 0);
		world.setBlock(i + 2, j + 2, k + 0, 0);
		world.setBlock(i + 2, j + 2, k + 1, 0);
		world.setBlock(i + 2, j + 2, k + 2, 0);
		world.setBlock(i + 2, j + 2, k + 3, 0);
		world.setBlock(i + 2, j + 2, k + 4, 0);
		world.setBlock(i + 2, j + 2, k + 5, 0);
		world.setBlock(i + 2, j + 2, k + 6, 0);
		world.setBlock(i + 2, j + 3, k + 0, 0);
		world.setBlock(i + 2, j + 3, k + 1, 0);
		world.setBlock(i + 2, j + 3, k + 2, 0);
		world.setBlock(i + 2, j + 3, k + 3, 0);
		world.setBlock(i + 2, j + 3, k + 4, 0);
		world.setBlock(i + 2, j + 3, k + 5, 0);
		world.setBlock(i + 2, j + 3, k + 6, 0);
		world.setBlock(i + 2, j + 4, k + 0, 0);
		world.setBlock(i + 2, j + 4, k + 1, 0);
		world.setBlock(i + 2, j + 4, k + 2, 0);
		world.setBlock(i + 2, j + 4, k + 3, 0);
		world.setBlock(i + 2, j + 4, k + 4, 0);
		world.setBlock(i + 2, j + 4, k + 5, 0);
		world.setBlock(i + 2, j + 4, k + 6, 0);
		world.setBlockAndMetadata(i + 2, j + 5, k + 0, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 2, j + 5, k + 1, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 2, j + 5, k + 2, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 2, j + 5, k + 3, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 2, j + 5, k + 4, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 2, j + 5, k + 5, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 2, j + 5, k + 6, Block.woodSingleSlab.blockID, 2);
		world.setBlock(i + 2, j + 6, k + 0, 0);
		world.setBlock(i + 2, j + 6, k + 1, 0);
		world.setBlock(i + 2, j + 6, k + 2, 0);
		world.setBlock(i + 2, j + 6, k + 3, 0);
		world.setBlock(i + 2, j + 6, k + 4, 0);
		world.setBlock(i + 2, j + 6, k + 5, 0);
		world.setBlock(i + 2, j + 6, k + 6, 0);
		world.setBlock(i + 3, j + 0, k + 0, themedBlockID);
		world.setBlockAndMetadata(i + 3, j + 0, k + 1, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 3, j + 0, k + 2, Block.woodSingleSlab.blockID, 2);
		world.setBlock(i + 3, j + 0, k + 3, themedCatcherID);
		world.setBlockAndMetadata(i + 3, j + 0, k + 4, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 3, j + 0, k + 5, Block.woodSingleSlab.blockID, 2);
		world.setBlock(i + 3, j + 0, k + 6, themedBlockID);
		world.setBlock(i + 3, j + 1, k + 0, 0);
		world.setBlock(i + 3, j + 1, k + 1, 0);
		world.setBlock(i + 3, j + 1, k + 2, 0);
		world.setBlock(i + 3, j + 1, k + 3, themedRefineryID);
		world.setBlock(i + 3, j + 1, k + 4, 0);
		world.setBlock(i + 3, j + 1, k + 5, 0);
		world.setBlock(i + 3, j + 1, k + 6, 0);
		world.setBlock(i + 3, j + 2, k + 0, 0);
		world.setBlock(i + 3, j + 2, k + 1, 0);
		world.setBlock(i + 3, j + 2, k + 2, 0);
		world.setBlock(i + 3, j + 2, k + 3, 0);
		world.setBlock(i + 3, j + 2, k + 4, 0);
		world.setBlock(i + 3, j + 2, k + 5, 0);
		world.setBlock(i + 3, j + 2, k + 6, 0);
		world.setBlock(i + 3, j + 3, k + 0, 0);
		world.setBlock(i + 3, j + 3, k + 1, 0);
		world.setBlock(i + 3, j + 3, k + 2, 0);
		world.setBlock(i + 3, j + 3, k + 3, 0);
		world.setBlock(i + 3, j + 3, k + 4, 0);
		world.setBlock(i + 3, j + 3, k + 5, 0);
		world.setBlock(i + 3, j + 3, k + 6, 0);
		world.setBlock(i + 3, j + 4, k + 0, 0);
		world.setBlock(i + 3, j + 4, k + 1, 0);
		world.setBlock(i + 3, j + 4, k + 2, 0);
		world.setBlock(i + 3, j + 4, k + 3, 0);
		world.setBlock(i + 3, j + 4, k + 4, 0);
		world.setBlock(i + 3, j + 4, k + 5, 0);
		world.setBlock(i + 3, j + 4, k + 6, 0);
		world.setBlockAndMetadata(i + 3, j + 5, k + 0, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 3, j + 5, k + 1, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 3, j + 5, k + 2, Block.woodSingleSlab.blockID, 2);
		world.setBlock(i + 3, j + 5, k + 3, Block.glass.blockID);
		world.setBlockAndMetadata(i + 3, j + 5, k + 4, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 3, j + 5, k + 5, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 3, j + 5, k + 6, Block.woodSingleSlab.blockID, 2);
		world.setBlock(i + 3, j + 6, k + 0, 0);
		world.setBlock(i + 3, j + 6, k + 1, 0);
		world.setBlock(i + 3, j + 6, k + 2, 0);
		world.setBlock(i + 3, j + 6, k + 3, 0);
		world.setBlock(i + 3, j + 6, k + 4, 0);
		world.setBlock(i + 3, j + 6, k + 5, 0);
		world.setBlock(i + 3, j + 6, k + 6, 0);
		world.setBlockAndMetadata(i + 4, j + 0, k + 0, Block.planks.blockID, 1);
		world.setBlockAndMetadata(i + 4, j + 0, k + 1, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 4, j + 0, k + 2, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 4, j + 0, k + 3, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 4, j + 0, k + 4, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 4, j + 0, k + 5, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 4, j + 0, k + 6, Block.planks.blockID, 1);
		world.setBlock(i + 4, j + 1, k + 0, 0);
		world.setBlock(i + 4, j + 1, k + 1, 0);
		world.setBlock(i + 4, j + 1, k + 2, 0);
		world.setBlock(i + 4, j + 1, k + 3, 0);
		world.setBlock(i + 4, j + 1, k + 4, 0);
		world.setBlock(i + 4, j + 1, k + 5, 0);
		world.setBlock(i + 4, j + 1, k + 6, 0);
		world.setBlock(i + 4, j + 2, k + 0, 0);
		world.setBlock(i + 4, j + 2, k + 1, 0);
		world.setBlock(i + 4, j + 2, k + 2, 0);
		world.setBlock(i + 4, j + 2, k + 3, 0);
		world.setBlock(i + 4, j + 2, k + 4, 0);
		world.setBlock(i + 4, j + 2, k + 5, 0);
		world.setBlock(i + 4, j + 2, k + 6, 0);
		world.setBlock(i + 4, j + 3, k + 0, 0);
		world.setBlock(i + 4, j + 3, k + 1, 0);
		world.setBlock(i + 4, j + 3, k + 2, 0);
		world.setBlock(i + 4, j + 3, k + 3, 0);
		world.setBlock(i + 4, j + 3, k + 4, 0);
		world.setBlock(i + 4, j + 3, k + 5, 0);
		world.setBlock(i + 4, j + 3, k + 6, 0);
		world.setBlock(i + 4, j + 4, k + 0, 0);
		world.setBlock(i + 4, j + 4, k + 1, 0);
		world.setBlock(i + 4, j + 4, k + 2, 0);
		world.setBlock(i + 4, j + 4, k + 3, 0);
		world.setBlock(i + 4, j + 4, k + 4, 0);
		world.setBlock(i + 4, j + 4, k + 5, 0);
		world.setBlock(i + 4, j + 4, k + 6, 0);
		world.setBlockAndMetadata(i + 4, j + 5, k + 0, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 4, j + 5, k + 1, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 4, j + 5, k + 2, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 4, j + 5, k + 3, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 4, j + 5, k + 4, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 4, j + 5, k + 5, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 4, j + 5, k + 6, Block.woodSingleSlab.blockID, 2);
		world.setBlock(i + 4, j + 6, k + 0, 0);
		world.setBlock(i + 4, j + 6, k + 1, 0);
		world.setBlock(i + 4, j + 6, k + 2, 0);
		world.setBlock(i + 4, j + 6, k + 3, 0);
		world.setBlock(i + 4, j + 6, k + 4, 0);
		world.setBlock(i + 4, j + 6, k + 5, 0);
		world.setBlock(i + 4, j + 6, k + 6, 0);
		world.setBlockAndMetadata(i + 5, j + 0, k + 0, Block.planks.blockID, 1);
		world.setBlockAndMetadata(i + 5, j + 0, k + 1, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 5, j + 0, k + 2, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 5, j + 0, k + 3, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 5, j + 0, k + 4, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 5, j + 0, k + 5, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 5, j + 0, k + 6, Block.planks.blockID, 1);
		world.setBlock(i + 5, j + 1, k + 0, Block.fence.blockID);
		world.setBlock(i + 5, j + 1, k + 1, 0);
		world.setBlock(i + 5, j + 1, k + 2, 0);
		world.setBlock(i + 5, j + 1, k + 3, 0);
		world.setBlock(i + 5, j + 1, k + 4, 0);
		world.setBlock(i + 5, j + 1, k + 5, 0);
		world.setBlock(i + 5, j + 1, k + 6, Block.fence.blockID);
		world.setBlock(i + 5, j + 2, k + 0, 0);
		world.setBlock(i + 5, j + 2, k + 1, 0);
		world.setBlock(i + 5, j + 2, k + 2, 0);
		world.setBlock(i + 5, j + 2, k + 3, 0);
		world.setBlock(i + 5, j + 2, k + 4, 0);
		world.setBlock(i + 5, j + 2, k + 5, 0);
		world.setBlock(i + 5, j + 2, k + 6, 0);
		world.setBlock(i + 5, j + 3, k + 0, 0);
		world.setBlock(i + 5, j + 3, k + 1, 0);
		world.setBlock(i + 5, j + 3, k + 2, 0);
		world.setBlock(i + 5, j + 3, k + 3, 0);
		world.setBlock(i + 5, j + 3, k + 4, 0);
		world.setBlock(i + 5, j + 3, k + 5, 0);
		world.setBlock(i + 5, j + 3, k + 6, 0);
		world.setBlock(i + 5, j + 4, k + 0, 0);
		world.setBlock(i + 5, j + 4, k + 1, 0);
		world.setBlock(i + 5, j + 4, k + 2, 0);
		world.setBlock(i + 5, j + 4, k + 3, 0);
		world.setBlock(i + 5, j + 4, k + 4, 0);
		world.setBlock(i + 5, j + 4, k + 5, 0);
		world.setBlock(i + 5, j + 4, k + 6, 0);
		world.setBlockAndMetadata(i + 5, j + 5, k + 0, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 5, j + 5, k + 1, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 5, j + 5, k + 2, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 5, j + 5, k + 3, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 5, j + 5, k + 4, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 5, j + 5, k + 5, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 5, j + 5, k + 6, Block.woodSingleSlab.blockID, 2);
		world.setBlock(i + 5, j + 6, k + 0, 0);
		world.setBlock(i + 5, j + 6, k + 1, 0);
		world.setBlock(i + 5, j + 6, k + 2, 0);
		world.setBlock(i + 5, j + 6, k + 3, 0);
		world.setBlock(i + 5, j + 6, k + 4, 0);
		world.setBlock(i + 5, j + 6, k + 5, 0);
		world.setBlock(i + 5, j + 6, k + 6, 0);
		world.setBlockAndMetadata(i + 6, j + 0, k + 0, Block.planks.blockID, 1);
		world.setBlockAndMetadata(i + 6, j + 0, k + 1, Block.planks.blockID, 1);
		world.setBlockAndMetadata(i + 6, j + 0, k + 2, Block.planks.blockID, 1);
		world.setBlock(i + 6, j + 0, k + 3, themedBlockID);
		world.setBlockAndMetadata(i + 6, j + 0, k + 4, Block.planks.blockID, 1);
		world.setBlockAndMetadata(i + 6, j + 0, k + 5, Block.planks.blockID, 1);
		world.setBlockAndMetadata(i + 6, j + 0, k + 6, Block.planks.blockID, 1);
		world.setBlock(i + 6, j + 1, k + 0, Block.fence.blockID);
		world.setBlock(i + 6, j + 1, k + 1, Block.fence.blockID);
		world.setBlock(i + 6, j + 1, k + 2, 0);
		world.setBlock(i + 6, j + 1, k + 3, 0);
		world.setBlock(i + 6, j + 1, k + 4, 0);
		world.setBlock(i + 6, j + 1, k + 5, Block.fence.blockID);
		world.setBlock(i + 6, j + 1, k + 6, Block.fence.blockID);
		world.setBlock(i + 6, j + 2, k + 0, Block.fence.blockID);
		world.setBlock(i + 6, j + 2, k + 1, 0);
		world.setBlock(i + 6, j + 2, k + 2, 0);
		world.setBlock(i + 6, j + 2, k + 3, 0);
		world.setBlock(i + 6, j + 2, k + 4, 0);
		world.setBlock(i + 6, j + 2, k + 5, 0);
		world.setBlock(i + 6, j + 2, k + 6, Block.fence.blockID);
		world.setBlock(i + 6, j + 3, k + 0, Block.fence.blockID);
		world.setBlock(i + 6, j + 3, k + 1, 0);
		world.setBlock(i + 6, j + 3, k + 2, 0);
		world.setBlock(i + 6, j + 3, k + 3, 0);
		world.setBlock(i + 6, j + 3, k + 4, 0);
		world.setBlock(i + 6, j + 3, k + 5, 0);
		world.setBlock(i + 6, j + 3, k + 6, Block.fence.blockID);
		world.setBlock(i + 6, j + 4, k + 0, Block.fence.blockID);
		world.setBlock(i + 6, j + 4, k + 1, 0);
		world.setBlock(i + 6, j + 4, k + 2, 0);
		world.setBlock(i + 6, j + 4, k + 3, 0);
		world.setBlock(i + 6, j + 4, k + 4, 0);
		world.setBlock(i + 6, j + 4, k + 5, 0);
		world.setBlock(i + 6, j + 4, k + 6, Block.fence.blockID);
		world.setBlockAndMetadata(i + 6, j + 5, k + 0, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 6, j + 5, k + 1, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 6, j + 5, k + 2, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 6, j + 5, k + 3, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 6, j + 5, k + 4, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 6, j + 5, k + 5, Block.woodSingleSlab.blockID, 2);
		world.setBlockAndMetadata(i + 6, j + 5, k + 6, Block.woodSingleSlab.blockID, 2);
		world.setBlock(i + 6, j + 6, k + 0, 0);
		world.setBlock(i + 6, j + 6, k + 1, 0);
		world.setBlock(i + 6, j + 6, k + 2, 0);
		world.setBlock(i + 6, j + 6, k + 3, 0);
		world.setBlock(i + 6, j + 6, k + 4, 0);
		world.setBlock(i + 6, j + 6, k + 5, 0);
		world.setBlock(i + 6, j + 6, k + 6, 0);

		return true;
	}
}