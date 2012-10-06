package shadowcraft.core;

import cpw.mods.fml.client.registry.RenderingRegistry;
import shadowcraft.core.CommonProxy;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraft.src.TileEntityRenderer;

public class ClientProxy extends CommonProxy
{
    public void registerRenderThings(){
    	MinecraftForgeClient.preloadTexture("/gui/scitemtex.png");
    	MinecraftForgeClient.preloadTexture("/gui/scblocktex.png");
    	MinecraftForgeClient.preloadTexture("/item/shadowcube.png");
    }
}