package shadowcraft.core;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.nio.ByteBuffer;

import shadowcraft.ShadowCraftShadow;
import shadowcraft.shadow.tileentity.TileEntityShadowCatcher;
import shadowcraft.shadow.tileentity.TileEntityShadowRefinery;

import buildcraft.api.liquids.LiquidStack;

import net.minecraft.client.Minecraft;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.Packet250CustomPayload;
import net.minecraft.src.TileEntity;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

// Create a class and implement IPacketHandler
// This just handles the data packets in the server
public class ClientPacketHandler implements IPacketHandler{
	
	@Override
	public void onPacketData(NetworkManager manager, Packet250CustomPayload payload, Player player){
		DataInputStream data = new DataInputStream(new ByteArrayInputStream(payload.data));
		int x = ByteBuffer.wrap(payload.data).getInt(0);
		int y = ByteBuffer.wrap(payload.data).getInt(4);
		int z = ByteBuffer.wrap(payload.data).getInt(8);
		int i = ByteBuffer.wrap(payload.data).getInt(12);
		Minecraft mc = Minecraft.getMinecraft();
		TileEntity te = mc.theWorld.getBlockTileEntity(x, y, z);
		if(te instanceof TileEntityShadowCatcher){
			((TileEntityShadowCatcher) te).setShadows(i);
		}
		else if(te instanceof TileEntityShadowRefinery){		
			if(i == -1){
				((TileEntityShadowRefinery) te).tank.setLiquid(new LiquidStack(ShadowCraftShadow.liquidShadowStill, 0));
			}
			else{
				((TileEntityShadowRefinery) te).tank.setLiquid(new LiquidStack(ShadowCraftShadow.liquidShadowStill, i));
			}
		}
	}
	
}
