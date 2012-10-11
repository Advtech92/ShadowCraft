package shadowcraft.core;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import net.minecraft.src.NetworkManager;
import net.minecraft.src.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class ServerPacketHandler implements IPacketHandler{

	@Override
	public void onPacketData(final NetworkManager manager, final Packet250CustomPayload payload, final Player player){
		new DataInputStream(new ByteArrayInputStream(payload.data));
	}
}
