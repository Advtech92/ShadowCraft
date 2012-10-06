package shadowcraft;

import java.util.logging.Logger;

import shadowcraft.core.ClientPacketHandler;
import shadowcraft.core.ClientProxy;
import shadowcraft.core.GuiHandler;
import shadowcraft.core.ServerPacketHandler;
import shadowcraft.core.WorldGeneratorShadowCraft;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "ShadowCraft|Core", name = "ShadowCraft Core", version = "0.0")
@NetworkMod(clientSideRequired=true, serverSideRequired=false,
clientPacketHandlerSpec = @SidedPacketHandler(channels = {"ShadowCraft"}, packetHandler = ClientPacketHandler.class),
serverPacketHandlerSpec = @SidedPacketHandler(channels = {"ShadowCraft"}, packetHandler = ServerPacketHandler.class))
public class ShadowCraft {
	
	@Instance("ShadowCraft|Core")
	public static ShadowCraft instance = new ShadowCraft();
	
	public static Logger scLog = Logger.getLogger("ShadowCraft");
	
	
	@SidedProxy(clientSide = "shadowcraft.core.ClientProxy", serverSide = "shadowcraft.core.CommonProxy")
    public static ClientProxy clientProxy;
	private GuiHandler guiHandler = new GuiHandler();
	
	@Init
	public void load(FMLInitializationEvent event){
		if(!(Loader.isModLoaded("ShadowCraft Shadow") || Loader.isModLoaded("ShadowCraft Light"))){
			scLog.severe("All ShadowCraft mods must be installed");
		}
		
		clientProxy.preLoadTextures();
		
		NetworkRegistry.instance().registerGuiHandler(this, guiHandler);

		GameRegistry.registerWorldGenerator(new WorldGeneratorShadowCraft());
		
	}

}