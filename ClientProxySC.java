package shadowcraft;

import cpw.mods.fml.client.registry.RenderingRegistry;
import shadowcraft.CommonProxySC;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraft.src.TileEntityRenderer;

public class ClientProxySC extends CommonProxySC
{
    public void registerRenderThings(){
    	MinecraftForgeClient.preloadTexture("/gui/scitemtex.png");
    	MinecraftForgeClient.preloadTexture("/gui/scblocktex.png");
    	TileEntityRenderer.instance.specialRendererMap.put(TileEntityShadowRefinery.class, new RenderShadowCube());
    }
}