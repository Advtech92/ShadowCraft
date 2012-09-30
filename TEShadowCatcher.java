package shadowcraft;

import buildcraft.api.core.Orientations;
import buildcraft.api.core.Position;
import buildcraft.api.liquids.ILiquidTank;
import buildcraft.api.liquids.ITankContainer;
import buildcraft.api.liquids.LiquidManager;
import buildcraft.api.liquids.LiquidStack;
import buildcraft.core.IMachine;
import buildcraft.core.network.TileNetworkData;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagList;
import net.minecraft.src.TileEntity;

public class TEShadowCatcher extends TileEntity implements IMachine {

	
	private ItemStack[] inventory;
	public @TileNetworkData
	int shadows;
	public @TileNetworkData
	int lightLevel;
	private boolean loaded = false;
	
	public TEShadowCatcher() {
		System.out.print("TileEntityShadowCatcher init, shadows: ");
		System.out.print(shadows);
		System.out.println();
		inventory = new ItemStack[1];
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound){
		super.readFromNBT(tagCompound);
        shadows = tagCompound.getInteger("Shadows");
        ShadowCraft.scLog.info(Integer.toString(shadows));
	}

	public void setShadows(int setTo){
		shadows = setTo;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tagCompound){
		super.writeToNBT(tagCompound);
        tagCompound.setInteger("Shadows", shadows);
        
	}
	
	public int getShadows(){
		return shadows;
	}
	
	
	public void updateEntity(){
		lightLevel = worldObj.getBlockLightValue(xCoord, yCoord + 1, zCoord);
		boolean isCheatyBlockAbove = !worldObj.isAirBlock(xCoord, yCoord + 1, zCoord);
		if((lightLevel < 3) && (!isCheatyBlockAbove) && (!(shadows == 10000))){
			shadows += 10;
		}
		if(shadows > 10000){
			shadows = 10000;
		}
		
		// Check for adjacent pipes and eject liquid shadow
		if (shadows == 10000) {
			for (int i = 0; i < 6; ++i) {
				Position p = new Position(xCoord, yCoord, zCoord, Orientations.values()[i]);
				p.moveForwards(1);

				TileEntity tile = worldObj.getBlockTileEntity((int) p.x, (int) p.y, (int) p.z);

				if(tile instanceof ITankContainer) {
					ShadowCraft.scLog.info(Integer.toString(((ITankContainer)tile).fill(p.orientation.reverse(), new LiquidStack(ShadowCraft.liquidShadowStill, (LiquidManager.BUCKET_VOLUME * 2) * ShadowCraft.shadowCatcherOutputMultiplier), true)));
					shadows = 0;
					break;
				}
			}
		}
	}

	@Override
	public boolean isActive() {
		return true;
	}

	@Override
	public boolean manageLiquids() {
		return true;
	}

	@Override
	public boolean manageSolids() {
		return false;
	}

	@Override
	public boolean allowActions() {
		return false;
	}

}
