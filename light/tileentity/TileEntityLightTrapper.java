package shadowcraft.light.tileentity;

import java.nio.ByteBuffer;

import shadowcraft.ShadowCraftLight;
import buildcraft.api.core.Orientations;
import buildcraft.api.core.Position;
import buildcraft.api.liquids.ITankContainer;
import buildcraft.api.liquids.LiquidManager;
import buildcraft.api.liquids.LiquidStack;
import buildcraft.core.IMachine;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.Packet;
import net.minecraft.src.Packet250CustomPayload;
import net.minecraft.src.TileEntity;

public class TileEntityLightTrapper extends TileEntity implements IMachine {

	public int light;
	public int lightLevel;
	
	public TileEntityLightTrapper() {
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound){
		super.readFromNBT(tagCompound);
        light = tagCompound.getInteger("light");
        super.updateEntity();
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tagCompound){
		super.writeToNBT(tagCompound);
        tagCompound.setInteger("light", light);
	}

	public void setLight(int setTo){
		light = setTo;
	}
	
	public int getLight(){
		return light;
	}
	
	
	@Override
	public void updateEntity(){
		lightLevel = worldObj.getBlockLightValue(xCoord, yCoord + 1, zCoord);
		boolean isCheatyBlockAbove = !worldObj.isAirBlock(xCoord, yCoord + 1, zCoord);
		if((lightLevel > 10) && (!isCheatyBlockAbove) && (!(light == 10000))){
			light += 10;
		}
		if(light > 10000){
			light = 10000;
		}
		
		if (light == 10000) {
			for (int i = 0; i < 6; ++i) {
				Position p = new Position(xCoord, yCoord, zCoord, Orientations.values()[i]);
				p.moveForwards(1);

				TileEntity tile = worldObj.getBlockTileEntity((int) p.x, (int) p.y, (int) p.z);

				if(tile instanceof ITankContainer) {
					((ITankContainer)tile).fill(p.orientation.reverse(), new LiquidStack(ShadowCraftLight.liquidLightStill, (LiquidManager.BUCKET_VOLUME * 2) * ShadowCraftLight.lightTrapperOutputMultiplier), true);
					light = 0;
					break;
				}
			}
		}
	}

	@Override
	public Packet getDescriptionPacket(){
		byte[] bytes = ByteBuffer.allocate(16).putInt(xCoord).putInt(yCoord).putInt(zCoord).putInt(light).array();
		Packet packet = new Packet250CustomPayload("ShadowCraft", bytes);
		return packet;
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
