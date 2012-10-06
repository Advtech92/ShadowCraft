package shadowcraft.shadow.tileentity;

import java.nio.ByteBuffer;
import java.util.Random;

import shadowcraft.ShadowCraftShadow;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import cpw.mods.fml.common.network.PacketDispatcher;
import buildcraft.api.core.Orientations;
import buildcraft.api.liquids.ILiquidTank;
import buildcraft.api.liquids.ITankContainer;
import buildcraft.api.liquids.LiquidManager;
import buildcraft.api.liquids.LiquidStack;
import buildcraft.api.liquids.LiquidTank;
import buildcraft.api.power.IPowerProvider;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerFramework;
import buildcraft.core.IMachine;
import buildcraft.core.network.TileNetworkData;
import buildcraft.factory.TileMachine;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagList;
import net.minecraft.src.Packet;
import net.minecraft.src.Packet250CustomPayload;

public class TileEntityShadowRefinery extends TileMachine implements IMachine, IPowerReceptor, IInventory, ITankContainer{

	public IPowerProvider powerProvider;
	public ItemStack[] inventory;
	public LiquidStack shadows;
	public ILiquidTank tank = new LiquidTank(LiquidManager.BUCKET_VOLUME * 10);
	public float cubeRotation = 0.0F;
	protected boolean cubeSpawned = false;
	public int refineryProgress = 0;
	@TileNetworkData
	public int guiProgress = 0;
	public boolean hasTankUpdate;
	
	public TileEntityShadowRefinery(){
		inventory = new ItemStack[1];
		powerProvider = PowerFramework.currentFramework.createPowerProvider();
		powerProvider.configure(20, 2, 10, 2, 1000);
	}
	
	@Override
	public void updateEntity(){
		cubeRotation += 1.0F;
		if(cubeRotation >= 360.0F){
			cubeRotation = 0.0F;
		}
		if(!(powerProvider.useEnergy(2.0F, 10.0F, true) >= 2.0F)){
			return;
		}
		refine();
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
    	refineryProgress = tagCompound.getInteger("Progress");
    	tank.setLiquid(LiquidStack.loadLiquidStackFromNBT(tagCompound));
    	
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
    	tagCompound.setInteger("Progress", refineryProgress);
    	if(tank.getLiquid() != null){
    		tank.getLiquid().writeToNBT(tagCompound);
    	}
	}

	@Override
	public void openChest(){	}

	@Override
	public void closeChest(){	}
	
	@Override
	public boolean isActive(){
		return true;
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
		powerProvider = provider;
	}

	@Override
	public IPowerProvider getPowerProvider(){
		return powerProvider;
	}

	@Override
	public void doWork(){
	}
	
	public void refine(){
		if(tank.getLiquid() == null || tank.getLiquid().amount < LiquidManager.BUCKET_VOLUME){
			refineryProgress = 0;
			return;
		}
		refineryProgress += 10;
		if(!(getStackInSlot(0) == null)){
			if(getStackInSlot(0).itemID != getStackInSlot(0).itemID){
				dropItems(0);
			}
		}
		int tGuiProgress = (refineryProgress * 25) / 10000;
		if(tGuiProgress != guiProgress){
			guiProgress = tGuiProgress;
			sendNetworkUpdate();
		}
		if(refineryProgress >= 10000){
			ItemStack newItem;
			ItemStack prevItem = getStackInSlot(0);
			if(prevItem == null){
				newItem = new ItemStack(ShadowCraftShadow.shadowIngot);
			}
			else{
				newItem = new ItemStack(ShadowCraftShadow.shadowIngot, prevItem.stackSize + 1);
			}
			setInventorySlotContents(0, newItem);
			refineryProgress = 0;
			tank.drain(1000, true);
            PacketDispatcher.sendPacketToAllAround(xCoord, yCoord, zCoord, 100, worldObj.getWorldInfo().getDimension(), getDescriptionPacket());
		}
	}
	
	@SideOnly(Side.SERVER)
	public void dropItems(int slot){
		Random rand = new Random();
		ItemStack item = getStackInSlot(slot);
		
		if (item != null && item.stackSize > 0) {
			float rx = rand.nextFloat() * 0.8F + 0.1F;
			float ry = rand.nextFloat() * 0.8F + 0.1F;
			float rz = rand.nextFloat() * 0.8F + 0.1F;
			
			EntityItem entityItem = new EntityItem(worldObj,
					xCoord + rx, yCoord + ry, zCoord + rz,
					new ItemStack(item.itemID, item.stackSize, item.getItemDamage()));
			
			if (item.hasTagCompound()) {
				entityItem.item.setTagCompound((NBTTagCompound) item.getTagCompound().copy());
			}
			
			float factor = 0.05F;
			entityItem.motionX = rand.nextGaussian() * factor;
			entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
			entityItem.motionZ = rand.nextGaussian() * factor;
			worldObj.spawnEntityInWorld(entityItem);
			item.stackSize = 0;
			inventory[slot] = null;
		}
	}
	
	@Override
    public int fill(Orientations from, LiquidStack resource, boolean doFill)
    {
		return fill(0, resource, doFill);
    }

	@Override
	public int fill(int tankIndex, LiquidStack resource, boolean doFill) {
		int totalUsed = 0;
		if(!(resource.isLiquidEqual(new LiquidStack(ShadowCraftShadow.liquidShadowStill, 1)))){
			return 0;
		}
		
        while(resource.amount > 0 && ((tank.getLiquid() == null) || (tank.getLiquid().amount != tank.getCapacity()))){
            int used = tank.fill(resource, doFill);
            resource.amount -= used;
            totalUsed += used;
        }
        if(totalUsed > 0){
            PacketDispatcher.sendPacketToAllAround(xCoord, yCoord, zCoord, 100, worldObj.getWorldInfo().getDimension(), getDescriptionPacket());
		}
        return totalUsed;
	}

	@Override
	public LiquidStack drain(Orientations from, int maxDrain, boolean doDrain) {
		return null;
	}

	@Override
	public LiquidStack drain(int tankIndex, int maxDrain, boolean doDrain) {
		return null;
	}

	@Override
	public ILiquidTank[] getTanks() {
		return new ILiquidTank[] { tank };
	}
	
	@Override
	public Packet getDescriptionPacket(){
		int amount = 0;
		if(tank.getLiquid() == null){
			amount = -1;
		}
		else{
			amount = tank.getLiquid().amount;
		}
		byte[] bytes = ByteBuffer.allocate(16).putInt(xCoord).putInt(yCoord).putInt(zCoord).putInt(amount).array();
		return new Packet250CustomPayload("ShadowCraft", bytes);
	}

}
