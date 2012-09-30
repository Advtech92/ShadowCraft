package shadowcraft;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.nio.ByteBuffer;

import net.minecraft.client.Minecraft;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.Packet132TileEntityData;
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
		int shadows = ByteBuffer.wrap(payload.data).getInt(12);
		int x = ByteBuffer.wrap(payload.data).getInt(0);
		int y = ByteBuffer.wrap(payload.data).getInt(4);
		int z = ByteBuffer.wrap(payload.data).getInt(8);
		Minecraft mc = Minecraft.getMinecraft();
		TileEntity te = mc.theWorld.getBlockTileEntity(x, y, z);
		if(te instanceof TEShadowCatcher){
			((TEShadowCatcher) te).setShadows(shadows);
		}
	}
	
}
