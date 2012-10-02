package shadowcraft;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import buildcraft.api.liquids.ILiquidTank;
import buildcraft.api.liquids.LiquidManager;
import buildcraft.api.liquids.LiquidStack;
import buildcraft.api.liquids.LiquidTank;
import buildcraft.api.power.IPowerProvider;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.core.IMachine;
import net.minecraft.client.Minecraft;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagList;
import net.minecraft.src.TileEntity;
import net.minecraft.src.WorldClient;

public class TileEntityShadowRefinery extends TileEntity implements IMachine, IPowerReceptor, IInventory{

	public ItemStack[] inventory;
	public LiquidStack shadows;
	public ILiquidTank tank = new LiquidTank(LiquidManager.BUCKET_VOLUME * 16);
	public float cubeRotation = 0.0F;
	protected boolean cubeSpawned = false;
	
	public TileEntityShadowRefinery(){
		inventory = new ItemStack[1];
	}
	
	@Override
	public void updateEntity(){
		cubeSpawned = true;
		
		// Will be replaced with a check to see if it's refining later.
		if(inventory[0] != null){
			cubeRotation += 10.0F;
		}
		else{
			cubeRotation += 0.5F;
		}
		if(cubeRotation >= 360.0F){
			cubeRotation = 0.0F;
		}
	}
	
	@Override
	public int getSizeInventory(){
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot){
		return inventory[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount){
		ItemStack itemStack = getStackInSlot(slot);
		if(itemStack != null){
			if(itemStack.stackSize <= amount){
				setInventorySlotContents(slot, null);
			}
			else{
				itemStack = itemStack.splitStack(amount);
				if(itemStack.stackSize == 0){
					setInventorySlotContents(slot, null);
				}
			}
		}
		return itemStack;
	}
	
	@Override
	public ItemStack getStackInSlotOnClosing(int slot){
		ItemStack itemStack = getStackInSlot(slot);
		if (itemStack != null) {
			setInventorySlotContents(slot, null);
		}
		return itemStack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemStack){
		inventory[slot] = itemStack;
		if(itemStack != null && itemStack.stackSize > getInventoryStackLimit()){
			itemStack.stackSize = getInventoryStackLimit();
		}
	}

	@Override
	public String getInvName(){
		return "Shadow Refinery";
	}

	@Override
	public int getInventoryStackLimit(){
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player){
		return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this && 
				player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tagCompound){
    	super.readFromNBT(tagCompound);
    	
    	NBTTagList tagList = tagCompound.getTagList("Inventory");
    	for (int i = 0; i < tagList.tagCount(); i++){
    		NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);
    		byte slot = tag.getByte("Slot");
    		if (slot >= 0 && slot < inventory.length){
    			inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
    		}
    	}
	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound){
    	super.writeToNBT(tagCompound);
    	
    	NBTTagList itemList = new NBTTagList();
    	for (int i = 0; i < inventory.length; i++){
    		ItemStack stack = inventory[i];
    		if (stack != null){
    			NBTTagCompound tag = new NBTTagCompound();
    			tag.setByte("Slot", (byte) i);
    			stack.writeToNBT(tag);
    			itemList.appendTag(tag);
    		}
    	}
    	tagCompound.setTag("Inventory", itemList);
	}

	@Override
	public void openChest(){	}

	@Override
	public void closeChest(){	}
	
	@Override
	public boolean isActive(){
		return false;
	}

	@Override
	public boolean manageLiquids(){
		return true;
	}

	@Override
	public boolean manageSolids(){
		return false;
	}

	@Override
	public boolean allowActions(){
		return false;
	}

	@Override
	public void setPowerProvider(IPowerProvider provider){
	}

	@Override
	public IPowerProvider getPowerProvider(){
		return null;
	}

	@Override
	public void doWork(){
	}

	@Override
	public int powerRequest(){
		return 0;
	}

}
