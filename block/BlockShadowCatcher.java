package shadowcraft.block;

import java.util.Random;

import shadowcraft.ShadowCraft;
import shadowcraft.TileEntityShadowCatcher;

import buildcraft.api.power.IPowerProvider;
import buildcraft.api.power.IPowerReceptor;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import net.minecraft.src.BlockContainer;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class BlockShadowCatcher extends BlockContainer{

	public TileEntityShadowCatcher te;
	private String[] list = {"smoke", "flame", "lava"};
	public BlockShadowCatcher(int par1) {
		super(par1, Material.rock);
		blockIndexInTexture = 5;
		setBlockName("blockTutorial");
		setCreativeTab(CreativeTabs.tabMisc);
	}
	
	@Override
    public String getTextureFile()
    {
            return "/gui/scblocktex.png";
    }
	
	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random par5Random)
    {

		int lightLevel = world.getBlockLightValue(x, y + 1, z);
		boolean isCheatyBlockAbove = !world.isAirBlock(x, y + 1, z);
		if((lightLevel < 3) && (!isCheatyBlockAbove)){
			Random r = new Random();
		    float var7 = (float)x + 0.5F;
		    float var8 = (float)y + 0.0F + par5Random.nextFloat() * 6.0F / 16.0F;
		    float var9 = (float)z + 0.5F;
		    float var10 = 0.52F;
		    float var11 = par5Random.nextFloat() * 0.6F - 0.3F;
		    world.spawnParticle(list[r.nextInt(list.length)], (double)(var7 - var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
		    world.spawnParticle(list[r.nextInt(list.length)], (double)(var7 + var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
		    world.spawnParticle(list[r.nextInt(list.length)], (double)(var7 + var11), (double)var8, (double)(var9 - var10), 0.0D, 0.0D, 0.0D);
		    world.spawnParticle(list[r.nextInt(list.length)], (double)(var7 + var11), (double)var8, (double)(var9 + var10), 0.0D, 0.0D, 0.0D);
		}
    }
	
	@Override
	public int getBlockTextureFromSide(int side){
		switch(side){
			case 0:
				return 7;
			case 1:
				return 6;
		}
		return blockIndexInTexture;
	}
    
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float f, float g, float t) {
		/*TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		
		if (tileEntity == null || player.isSneaking()) {
			return false;
		}
		
		player.openGui(ShadowCraft.instance, 0, world, x, y, z);
		*/
		ShadowCraft.scLog.info("Shadows: " + te.shadows);
		return true;
	}
	
	
	@Override
	public void breakBlock(World world, int x, int y, int z, int i, int j) {
		dropItems(world, x, y, z);
		
		super.breakBlock(world, x, y, z, i, j);
	}
	
	public void dropItems(World world, int x, int y, int z) {
		Random rand = new Random();

		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

		if(!(tileEntity instanceof IInventory)){
			return;
		}

		IInventory inventory = (IInventory) tileEntity;

		for(int i = 0; i < inventory.getSizeInventory(); i++){
			ItemStack item = inventory.getStackInSlot(i);

			if(item != null && item.stackSize > 0){
				float rx = rand.nextFloat() * 0.6F + 0.1F;
				float ry = rand.nextFloat() * 0.6F + 0.1F;
				float rz = rand.nextFloat() * 0.6F + 0.1F;

				EntityItem entity_item = new EntityItem(world, x + rx, y + ry, z + rz, new ItemStack(item.itemID, item.stackSize, item.getItemDamage()));

				if(item.hasTagCompound()){
					entity_item.item.setTagCompound((NBTTagCompound) item.getTagCompound().copy());
				}

				float factor = 0.5F;

				entity_item.motionX = rand.nextGaussian() * factor;
				entity_item.motionY = rand.nextGaussian() * factor + 0.2F;
				entity_item.motionZ = rand.nextGaussian() * factor;
				world.spawnEntityInWorld(entity_item);
				item.stackSize = 0;
			}
		}
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		System.out.println("createTileEntity called.");
		te = new TileEntityShadowCatcher();
		return te;
	}


	
}
