package shadowcraft;

import java.util.logging.Logger;

import buildcraft.api.liquids.LiquidData;
import buildcraft.api.liquids.LiquidManager;
import buildcraft.api.liquids.LiquidStack;

import shadowcraft.core.ClientPacketHandler;
import shadowcraft.core.ClientProxy;
import shadowcraft.core.GuiHandler;
import shadowcraft.core.ServerPacketHandler;
import shadowcraft.core.WorldGeneratorShadowCraft;
import shadowcraft.core.block.BlockShadowCraft;
import shadowcraft.core.item.ItemShadowCraft;
import shadowcraft.shadow.ClientTickHandler;
import shadowcraft.shadow.ModelShadowCube;
import shadowcraft.shadow.RenderShadowCube;
import shadowcraft.shadow.block.BlockDarkOre;
import shadowcraft.shadow.block.BlockFlowingShadow;
import shadowcraft.shadow.block.BlockShadowCatcher;
import shadowcraft.shadow.block.BlockShadowRefinery;
import shadowcraft.shadow.block.BlockStillShadow;
import shadowcraft.shadow.item.ItemShadowArmor;
import shadowcraft.shadow.item.ItemShadowAxe;
import shadowcraft.shadow.item.ItemShadowBucket;
import shadowcraft.shadow.item.ItemShadowIngot;
import shadowcraft.shadow.item.ItemShadowPickaxe;
import shadowcraft.shadow.item.ItemShadowShovel;
import shadowcraft.shadow.item.ItemShadowSword;
import shadowcraft.shadow.tileentity.TileEntityShadowCatcher;
import shadowcraft.shadow.tileentity.TileEntityShadowRefinery;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry; 
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;

import net.minecraft.client.Minecraft;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EnumArmorMaterial;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.Item;
import net.minecraft.src.BlockFlowing;
import net.minecraft.src.BlockOre;
import net.minecraft.src.BlockStationary;
import net.minecraft.src.Item;
import net.minecraft.src.ItemAxe;
import net.minecraft.src.ItemBucket;
import net.minecraft.src.ItemPickaxe;
import net.minecraft.src.ItemSpade;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Block;
import net.minecraft.src.ItemSword;
import net.minecraft.src.Material;
import net.minecraft.src.ModLoader;
import net.minecraft.src.TileEntityRenderer;

import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.event.entity.player.*;

@Mod(modid = "ShadowCraft|Core", name = "ShadowCraft Core", version = "0.0")
@NetworkMod(clientSideRequired=true, serverSideRequired=false,
clientPacketHandlerSpec = @SidedPacketHandler(channels = {"ShadowCraft"}, packetHandler = ClientPacketHandler.class),
serverPacketHandlerSpec = @SidedPacketHandler(channels = {"ShadowCraft"}, packetHandler = ServerPacketHandler.class))
public class ShadowCraft {
	
	@Instance("ShadowCraft|Core")
	public static ShadowCraft instance = new ShadowCraft();
	
	public static Logger scLog = Logger.getLogger("ShadowCraft");
	
	
	@SidedProxy(clientSide = "shadowcraft.core.ClientProxy", serverSide = "shadowcraft.core.CommonProxy")
    public static ClientProxy proxy;
	private GuiHandler guiHandler = new GuiHandler();
	
	@Init
	public void load(FMLInitializationEvent event){
		if(!Loader.isModLoaded("ShadowCraft Shadow")){
			scLog.severe("All ShadowCraft mods must be installed");
		}
		NetworkRegistry.instance().registerGuiHandler(this, guiHandler);

		GameRegistry.registerWorldGenerator(new WorldGeneratorShadowCraft());
		
	}

}