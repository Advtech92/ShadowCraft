package shadowcraft.core;

import shadowcraft.core.CommonProxy;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy
{
    public void preLoadTextures(){
    	MinecraftForgeClient.preloadTexture("/gui/scitemtex.png");
    	MinecraftForgeClient.preloadTexture("/gui/scblocktex.png");
    	MinecraftForgeClient.preloadTexture("/item/shadowcube.png");
    }
}