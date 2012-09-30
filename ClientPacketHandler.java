package shadowcraft;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.Packet132TileEntityData;
import net.minecraft.src.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

// Create a class and implement IPacketHandler
// This just handles the data packets in the server
public class ClientPacketHandler implements IPacketHandler{
	
	@Override
	public void onPacketData(NetworkManager manager, Packet250CustomPayload payload, Player player){
		System.out.println("[SHADOWCRAFT] ClientPacketHandler -> onPacketData()");
		DataInputStream data = new DataInputStream(new ByteArrayInputStream(payload.data));
	}
	
}
