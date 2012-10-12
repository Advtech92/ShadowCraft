package shadowcraft.light.block;

import java.util.Random;

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
import shadowcraft.ShadowCraft;
import shadowcraft.light.tileentity.TileEntityLightRefinery;

public class BlockLightRefinery extends BlockContainer{

	public BlockLightRefinery(final int itemID){
		super(itemID, Material.rock);
		blockIndexInTexture = (16 * 2) + 8;
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
		setLightOpacity(0);
		setCreativeTab(CreativeTabs.tabDecorations);
	}

	@Override
	public String getTextureFile(){
		return "/gui/scblocktex.png";
	}

	@Override
	public boolean renderAsNormalBlock(){
		return false;
	}

	@Override
	public int getBlockTextureFromSide(final int side){
		switch (side) {
		case 0:
			return (16 * 2) + 10;
		case 1:
			return (16 * 2) + 9;
		}
		return blockIndexInTexture;
	}

	@Override
	public boolean onBlockActivated(final World world, final int x, final int y, final int z,
		final EntityPlayer player, final int i, final float j, final float k, final float l){
		final TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if ((tileEntity == null) || player.isSneaking()) {
			return false;
		}
		player.openGui(ShadowCraft.instance, 0, world, x, y, z);
		return true;
	}

	@Override
	public void breakBlock(final World world, final int x, final int y, final int z, final int par5, final int par6){
		dropItems(world, x, y, z);
		super.breakBlock(world, x, y, z, par5, par6);
	}

	private void dropItems(final World world, final int x, final int y, final int z){
		final Random rand = new Random();

		final TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if (!(tileEntity instanceof IInventory)) {
			return;
		}
		final IInventory inventory = (IInventory) tileEntity;

		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			final ItemStack item = inventory.getStackInSlot(i);

			if ((item != null) && (item.stackSize > 0)) {
				final float rx = (rand.nextFloat() * 0.8F) + 0.1F;
				final float ry = (rand.nextFloat() * 0.8F) + 0.1F;
				final float rz = (rand.nextFloat() * 0.8F) + 0.1F;

				final EntityItem entityItem = new EntityItem(world, x + rx, y + ry, z + rz, new ItemStack(item.itemID,
					item.stackSize, item.getItemDamage()));

				if (item.hasTagCompound()) {
					entityItem.item.setTagCompound((NBTTagCompound) item.getTagCompound().copy());
				}

				final float factor = 0.05F;
				entityItem.motionX = rand.nextGaussian() * factor;
				entityItem.motionY = (rand.nextGaussian() * factor) + 0.2F;
				entityItem.motionZ = rand.nextGaussian() * factor;
				world.spawnEntityInWorld(entityItem);
				item.stackSize = 0;
			}
		}
	}

	@Override
	public TileEntity createNewTileEntity(final World world){
		return new TileEntityLightRefinery();
	}

}
