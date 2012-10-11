package shadowcraft.core;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import shadowcraft.shadow.ContainerRefinery;
import shadowcraft.shadow.GuiShadowRefinery;
import shadowcraft.shadow.tileentity.TileEntityShadowRefinery;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{

	@Override
	public Object getServerGuiElement(final int id, final EntityPlayer player, final World world, final int x,
		final int y, final int z){
		final TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if (tileEntity instanceof TileEntityShadowRefinery) {
			return new ContainerRefinery(player.inventory, (TileEntityShadowRefinery) tileEntity);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(final int id, final EntityPlayer player, final World world, final int x,
		final int y, final int z){
		final TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if (tileEntity instanceof TileEntityShadowRefinery) {
			return new GuiShadowRefinery(player.inventory, (TileEntityShadowRefinery) tileEntity);
		}
		return null;
	}

}
