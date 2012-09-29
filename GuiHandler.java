package shadowcraft;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		System.out.println("getServerGuiElement called.");
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		
		if (tileEntity instanceof TileEntityShadowCatcher) {
			return new ContainerShadowCatcher((TileEntityShadowCatcher)tileEntity, player.inventory);
		}
		
		return null;
	}

	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		System.out.println("getClientGuiElement called.");
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		
		if (tileEntity instanceof TileEntityShadowCatcher) {
			return new GuiShadowCatcher(player.inventory, (TileEntityShadowCatcher)tileEntity, ((TileEntityShadowCatcher) tileEntity).shadows);
		}
		
		return null;
	}

}
