package shadowcraft.shadow;

import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy
{
    public void preLoadTextures(){
    	MinecraftForgeClient.preloadTexture("/gui/scitemtex.png");
    	MinecraftForgeClient.preloadTexture("/gui/scblocktex.png");
    	MinecraftForgeClient.preloadTexture("/item/shadowcube.png");
    }
}
