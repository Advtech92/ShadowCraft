package shadowcraft;

import java.util.logging.Logger;

import buildcraft.api.liquids.LiquidData;
import buildcraft.api.liquids.LiquidManager;
import buildcraft.api.liquids.LiquidStack;

import shadowcraft.light.block.BlockFlowingLight;
import shadowcraft.light.block.BlockLightTrapper;
import shadowcraft.light.block.BlockStillLight;
import shadowcraft.light.item.ItemLightBucket;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraftforge.common.Configuration;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "ShadowCraft|Light", name = "ShadowCraft Light", version = "0.0")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class ShadowCraftLight {
	
	public static Block lightTrapper;
	public static Block liquidLightMoving;
	public static Block liquidLightStill;
	
	public static Item glassBucket;
	public static Item lightBucket;
	
	public static int lightTrapperID;
	public static int liquidLightMovingID;
	public static int liquidLightStillID;
	
	public static int glassBucketID;
	public static int lightBucketID;
	
	public static Logger scLog = Logger.getLogger("ShadowCraft|Light");
	
	@Instance("ShadowCraft|Light")
	public static ShadowCraftLight instance = new ShadowCraftLight();
	
	@PreInit
	public void preLoad(FMLPreInitializationEvent event){
		scLog.setParent(FMLLog.getLogger());
		
		scLog.info("Loading/creating config");
		loadConfig(event);
	}
	
	@Init
	public void load(FMLInitializationEvent event){
		addBlocks();
		
		addItems();
		
		LiquidManager.liquids.add(new LiquidData(new LiquidStack(liquidLightStill, LiquidManager.BUCKET_VOLUME), new LiquidStack(liquidLightMoving, LiquidManager.BUCKET_VOLUME), new ItemStack(lightBucket), new ItemStack(glassBucket)));
	}
	
	public void loadConfig(FMLPreInitializationEvent event){
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		
		lightTrapperID = config.get(Configuration.CATEGORY_BLOCK, "Light Trapper", 180).getInt(180);
		liquidLightMovingID = config.get(Configuration.CATEGORY_BLOCK, "Flowing Liquid Light", 181).getInt(181);
		liquidLightStillID = config.get(Configuration.CATEGORY_BLOCK, "Still Liquid Light", 182).getInt(182);
		
		glassBucketID = config.get(Configuration.CATEGORY_ITEM, "Glass Bucket", 220).getInt(220);
		glassBucketID = config.get(Configuration.CATEGORY_ITEM, "Light Bucket", 221).getInt(221);
		
		config.save();
	}
	
	public void addBlocks(){
		lightTrapper = new BlockLightTrapper(lightTrapperID).setHardness(5.0F).setResistance(2000.0F);
		GameRegistry.registerBlock(lightTrapper);
		LanguageRegistry.addName(lightTrapper, "Light Trapper");
		
		liquidLightMoving = new BlockFlowingLight(liquidLightMovingID, Material.water).setHardness(100.0F).setLightOpacity(3).setBlockName("liquidLightMoving");
		GameRegistry.registerBlock(liquidLightMoving);
		LanguageRegistry.addName(liquidLightMoving, "Flowing Liquid Light");
		
		liquidLightStill = new BlockStillLight(liquidLightStillID, Material.water).setHardness(100.0F).setLightOpacity(3).setBlockName("liquidLightStill");
		GameRegistry.registerBlock(liquidLightStill);
		LanguageRegistry.addName(liquidLightStill, "Still Liquid Light");
	}
	
	public void addItems(){
		glassBucket = new ItemLightBucket(glassBucketID, 0).setIconIndex(16).setItemName("glassBucket");
		LanguageRegistry.addName(glassBucket, "Glass Bucket");
		
		lightBucket = new ItemLightBucket(lightBucketID, liquidLightMovingID).setIconIndex(16 + 1).setItemName("lightBucket");
		LanguageRegistry.addName(lightBucket, "Light Bucket");
	}
	
}
