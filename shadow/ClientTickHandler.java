package shadowcraft.shadow;

import java.util.EnumSet;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import shadowcraft.ShadowCraftShadow;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ClientTickHandler implements ITickHandler{

	private final Minecraft mc = Minecraft.getMinecraft();
	public ItemStack[] armorInventory;
	public ItemStack shadowHelmetStack;
	public ItemStack shadowChestplateStack;
	public ItemStack shadowLeggingsStack;
	public ItemStack shadowBootsStack;
	public Random random;

	public ClientTickHandler(){
		shadowHelmetStack = new ItemStack(ShadowCraftShadow.shadowHelmet);
		shadowChestplateStack = new ItemStack(ShadowCraftShadow.shadowChestplate);
		shadowLeggingsStack = new ItemStack(ShadowCraftShadow.shadowLeggings);
		shadowBootsStack = new ItemStack(ShadowCraftShadow.shadowBoots);
		random = new Random();
	}

	@Override
	public void tickStart(final EnumSet<TickType> type, final Object... tickData){}

	@Override
	public void tickEnd(final EnumSet<TickType> type, final Object... tickData){
		if (type.equals(EnumSet.of(TickType.CLIENT))) {
			final GuiScreen guiscreen = Minecraft.getMinecraft().currentScreen;
			if (guiscreen != null) {
				onTickInGUI(guiscreen);
			} else {
				onTickInGame();
			}
		}
	}

	@Override
	public EnumSet<TickType> ticks(){
		return EnumSet.of(TickType.RENDER, TickType.CLIENT);
	}

	@Override
	public String getLabel(){
		return null;
	}

	public void onRenderTick(){}

	public void onTickInGUI(final GuiScreen guiscreen){}

	public void onTickInGame(){
		armorInventory = mc.thePlayer.inventory.armorInventory;
		if ((ShadowCraftShadow.shadowArmorParticles != 0) && (armorInventory[0] != null) && (armorInventory[1] != null)
			&& (armorInventory[2] != null) && (armorInventory[3] != null)) {
			if ((armorInventory.length == 4) && armorInventory[0].getItemName().equalsIgnoreCase("item.shadowBoots")
				&& armorInventory[1].getItemName().equalsIgnoreCase("item.shadowLeggings")
				&& armorInventory[2].getItemName().equalsIgnoreCase("item.shadowChestplate")
				&& armorInventory[3].getItemName().equalsIgnoreCase("item.shadowHelmet")) {

				final double yaw = Math.toRadians(mc.thePlayer.rotationYaw + 90);
				final double cosYaw = Math.cos(yaw);
				MathHelper.sin((float) yaw);
				final Random r = new Random();
				for (int i = 0; i < (ShadowCraftShadow.shadowArmorParticles * 10); ++i) {
					double randomNo = (r.nextDouble() / 3) + 0.02;
					final double randomNo2 = (r.nextDouble() * 0.5) + 0.3;
					mc.theWorld.spawnParticle("smoke", mc.thePlayer.posX - (cosYaw * randomNo2), mc.thePlayer.posY
						+ randomNo, mc.thePlayer.posZ - (Math.sin(yaw) * randomNo2), 0.0D, 0.0D, 0.0D);
					randomNo = (r.nextDouble() / 2) - 0.6;
					mc.theWorld.spawnParticle("smoke", mc.thePlayer.posX - (cosYaw * randomNo2), mc.thePlayer.posY
						+ randomNo, mc.thePlayer.posZ - (Math.sin(yaw) * randomNo2), 0.0D, 0.0D, 0.0D);
					randomNo = (r.nextDouble() / 3) - 1;
					mc.theWorld.spawnParticle("smoke", mc.thePlayer.posX - (cosYaw * randomNo2), mc.thePlayer.posY
						+ randomNo, mc.thePlayer.posZ - (Math.sin(yaw) * randomNo2), 0.0D, 0.0D, 0.0D);
					randomNo = (r.nextDouble() / 4) - 1.3;
					mc.theWorld.spawnParticle("smoke", mc.thePlayer.posX - (cosYaw * randomNo2), mc.thePlayer.posY
						+ randomNo, mc.thePlayer.posZ - (Math.sin(yaw) * randomNo2), 0.0D, 0.0D, 0.0D);
				}
			}
		}
	}
}
