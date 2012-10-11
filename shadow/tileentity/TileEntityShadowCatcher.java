package shadowcraft.shadow.tileentity;

import java.nio.ByteBuffer;

import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.Packet;
import net.minecraft.src.Packet250CustomPayload;
import net.minecraft.src.TileEntity;
import shadowcraft.ShadowCraftShadow;
import buildcraft.api.core.Orientations;
import buildcraft.api.core.Position;
import buildcraft.api.liquids.ITankContainer;
import buildcraft.api.liquids.LiquidManager;
import buildcraft.api.liquids.LiquidStack;
import buildcraft.core.IMachine;

public class TileEntityShadowCatcher extends TileEntity implements IMachine{

	public int shadows;
	public int lightLevel;

	public TileEntityShadowCatcher(){}

	@Override
	public void readFromNBT(final NBTTagCompound tagCompound){
		super.readFromNBT(tagCompound);
		shadows = tagCompound.getInteger("shadows");
		super.updateEntity();
	}

	@Override
	public void writeToNBT(final NBTTagCompound tagCompound){
		super.writeToNBT(tagCompound);
		tagCompound.setInteger("shadows", shadows);
	}

	public void setShadows(final int setTo){
		shadows = setTo;
	}

	public int getShadows(){
		return shadows;
	}

	@Override
	public void updateEntity(){
		lightLevel = worldObj.getBlockLightValue(xCoord, yCoord + 1, zCoord);
		final boolean isCheatyBlockAbove = !worldObj.isAirBlock(xCoord, yCoord + 1, zCoord);

		if ((lightLevel < 3) && (!isCheatyBlockAbove) && (!(shadows == 10000))) {
			shadows += 10;
		}
		if (shadows > 10000) {
			shadows = 10000;
		}

		// Check for adjacent pipes and eject liquid shadow
		if (shadows == 10000) {
			for (int i = 0; i < 6; ++i) {
				final Position p = new Position(xCoord, yCoord, zCoord, Orientations.values()[i]);
				p.moveForwards(1);

				final TileEntity tile = worldObj.getBlockTileEntity((int) p.x, (int) p.y, (int) p.z);

				if (tile instanceof ITankContainer) {
					((ITankContainer) tile).fill(p.orientation.reverse(), new LiquidStack(
						ShadowCraftShadow.liquidShadowStill, (LiquidManager.BUCKET_VOLUME * 2)
							* ShadowCraftShadow.shadowCatcherOutputMultiplier), true);
					shadows = 0;
					break;
				}
			}
		}
	}

	@Override
	public Packet getDescriptionPacket(){
		final byte[] bytes = ByteBuffer.allocate(16).putInt(xCoord).putInt(yCoord).putInt(zCoord).putInt(shadows)
			.array();
		final Packet packet = new Packet250CustomPayload("ShadowCraft", bytes);
		return packet;
	}

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

}
