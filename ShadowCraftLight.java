package shadowcraft;

import java.util.logging.Logger;

import buildcraft.api.liquids.LiquidData;
import buildcraft.api.liquids.LiquidManager;
import buildcraft.api.liquids.LiquidStack;

import shadowcraft.core.item.ItemShadowCraft;
import shadowcraft.core.item.ItemShadowCraftArmor;
import shadowcraft.core.item.ItemShadowCraftAxe;
import shadowcraft.core.item.ItemShadowCraftPickaxe;
import shadowcraft.core.item.ItemShadowCraftShovel;
import shadowcraft.core.item.ItemShadowCraftSword;
import shadowcraft.light.block.BlockFlowingLight;
import shadowcraft.light.block.BlockLightTrapper;
import shadowcraft.light.block.BlockStillLight;
import shadowcraft.light.item.ItemLightBucket;
import shadowcraft.light.tileentity.TileEntityLightTrapper;

import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EnumArmorMaterial;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;

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
	public static Item lightIngot;
	public static Item lightHelmet;
	public static Item lightChestplate;
	public static Item lightLeggings;
	public static Item lightBoots;
	public static Item lightSword;
	public static Item lightShovel;
	public static Item lightPickaxe;
	public static Item lightAxe;
	public static Item lightCrystal;
	
	public static int lightTrapperID;
	public static int liquidLightMovingID;
	public static int liquidLightStillID;
	
	public static int glassBucketID;
	public static int lightBucketID;
	public static int lightIngotID;
	public static int lightHelmetID;
	public static int lightChestplateID;
	public static int lightLeggingsID;
	public static int lightBootsID;
	public static int lightSwordID;
	public static int lightShovelID;
	public static int lightPickaxeID;
	public static int lightAxeID;
	public static int lightCrystalID;
	
	public static int lightTrapperOutputMultiplier;
	
	public static Logger scLog = Logger.getLogger("ShadowCraft|Light");
	
	public static EnumArmorMaterial lightArmorMaterial = EnumHelper.addArmorMaterial("Light", 55, new int[]{4, 7, 5, 4}, 20);
	public static EnumToolMaterial lightToolMaterial = EnumHelper.addToolMaterial("Light", 3, 5000, 8.0F, 2, 20);

	public static String lightArmorTex1 = "/armor/light_1.png";
	public static String lightArmorTex2 = "/armor/light_2.png";
	
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
		
		GameRegistry.registerTileEntity(TileEntityLightTrapper.class, "tileEntityLightTrapper");
		
		LiquidManager.liquids.add(new LiquidData(new LiquidStack(liquidLightStill, LiquidManager.BUCKET_VOLUME), new LiquidStack(liquidLightMoving, LiquidManager.BUCKET_VOLUME), new ItemStack(lightBucket), new ItemStack(glassBucket)));
	}
	
	public void loadConfig(FMLPreInitializationEvent event){
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		
		lightTrapperID = config.get(Configuration.CATEGORY_BLOCK, "Light Trapper", 180).getInt(180);
		liquidLightMovingID = config.get(Configuration.CATEGORY_BLOCK, "Flowing Liquid Light", 181).getInt(181);
		liquidLightStillID = config.get(Configuration.CATEGORY_BLOCK, "Still Liquid Light", 182).getInt(182);
		
		glassBucketID = config.get(Configuration.CATEGORY_ITEM, "Glass Bucket", 220).getInt(220);
		lightBucketID = config.get(Configuration.CATEGORY_ITEM, "Light Bucket", 221).getInt(221);
		lightIngotID = config.get(Configuration.CATEGORY_ITEM, "Light Ingot", 222).getInt(222);
		lightHelmetID = config.get(Configuration.CATEGORY_ITEM, "Light Helmet", 223).getInt(223);
		lightChestplateID = config.get(Configuration.CATEGORY_ITEM, "Light Chestplate", 224).getInt(224);
		lightLeggingsID = config.get(Configuration.CATEGORY_ITEM, "Light Leggings", 225).getInt(225);
		lightBootsID = config.get(Configuration.CATEGORY_ITEM, "Light Boots", 226).getInt(226);
		lightSwordID = config.get(Configuration.CATEGORY_ITEM, "Light Sword", 227).getInt(227);
		lightShovelID = config.get(Configuration.CATEGORY_ITEM, "Light Shovel", 228).getInt(228);
		lightPickaxeID = config.get(Configuration.CATEGORY_ITEM, "Light Pickaxe", 229).getInt(229);
		lightAxeID = config.get(Configuration.CATEGORY_ITEM, "Light Axe", 330).getInt(330);
		lightCrystalID = config.get(Configuration.CATEGORY_ITEM, "Glowing Crystal", 331).getInt(331);
		
		config.get(Configuration.CATEGORY_GENERAL, "Shadow Catcher Output", 1).comment = "Amount of liquid produced by light trapper. 1 = normal, 2 = 2x normal, 3 = 3x normal, etc.";
		lightTrapperOutputMultiplier = config.get(Configuration.CATEGORY_GENERAL, "Light Trapper Output", 1).getInt(1);

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
		
		lightIngot = new ItemShadowCraft(lightIngotID).setIconIndex(16 + 3).setCreativeTab(CreativeTabs.tabMaterials).setItemName("lightIngot");
		LanguageRegistry.addName(lightIngot, "Light Ingot");
		
		lightHelmet = new ItemShadowCraftArmor(lightHelmetID, lightArmorMaterial, ModLoader.addArmor("Light"), 0, lightArmorTex1, lightArmorTex2).setIconIndex(14).setItemName("lightHelmet");
		LanguageRegistry.addName(lightHelmet, "Light Helmet");
		
		lightChestplate = new ItemShadowCraftArmor(lightChestplateID, lightArmorMaterial, ModLoader.addArmor("Light"), 1, lightArmorTex1, lightArmorTex2).setIconIndex(16 + 14).setItemName("lightChestplate");
		LanguageRegistry.addName(lightChestplate, "Light Chestplate");
		
		lightLeggings = new ItemShadowCraftArmor(lightLeggingsID, lightArmorMaterial, ModLoader.addArmor("Light"), 2, lightArmorTex1, lightArmorTex2).setIconIndex((16 * 2) + 14).setItemName("lightLeggings");
		LanguageRegistry.addName(lightLeggings, "Light Leggings");
		
		lightBoots = new ItemShadowCraftArmor(lightBootsID, lightArmorMaterial, ModLoader.addArmor("Light"), 3, lightArmorTex1, lightArmorTex2).setIconIndex((16 * 3) + 14).setItemName("lightBoots");
		LanguageRegistry.addName(lightBoots, "Light Boots");
		
        lightSword = new ItemShadowCraftSword(lightSwordID, lightToolMaterial).setIconIndex((16 * 4) + 14).setItemName("lightSword");
        LanguageRegistry.addName(lightSword, "Glowing Rapier");
        
        lightShovel = new ItemShadowCraftShovel(lightShovelID, lightToolMaterial).setIconIndex((16 * 5) + 14).setItemName("lightShovel");
        LanguageRegistry.addName(lightShovel, "Gravel Annihilator");
        
        lightPickaxe = new ItemShadowCraftPickaxe(lightPickaxeID, lightToolMaterial).setIconIndex((16 * 6) + 14).setItemName("lightPickaxe");
        LanguageRegistry.addName(lightPickaxe, "Land Leveler");
        
        lightAxe = new ItemShadowCraftAxe(lightAxeID, lightToolMaterial).setIconIndex((16 * 7) + 14).setItemName("lightAxe");
        LanguageRegistry.addName(lightAxe, "Carbon Crusher");
	}
	
}
