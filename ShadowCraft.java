package shadowcraft;

import java.util.logging.Logger;

import buildcraft.api.liquids.LiquidData;
import buildcraft.api.liquids.LiquidManager;
import buildcraft.api.liquids.LiquidStack;

import shadowcraft.block.FlowingShadow;
import shadowcraft.block.ShadowCatcher;
import shadowcraft.block.ShadowRefinery;
import shadowcraft.block.StillShadow;
import shadowcraft.client.ClientProxySC;
import shadowcraft.item.ShadowArmor;
import shadowcraft.item.ShadowAxe;
import shadowcraft.item.ShadowBucket;
import shadowcraft.item.ShadowIngot;
import shadowcraft.item.ShadowPickaxe;
import shadowcraft.item.ShadowShovel;
import shadowcraft.item.ShadowSword;

import cpw.mods.fml.common.FMLLog;
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
import cpw.mods.fml.common.registry.GameRegistry; 
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;

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

import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.event.entity.player.*;;

@Mod(modid = "ShadowCraft", name = "ShadowCraft", version = "0.0")
@NetworkMod(clientSideRequired=true, serverSideRequired=false,
clientPacketHandlerSpec = @SidedPacketHandler(channels = {"ShadowCraft"}, packetHandler = ClientPacketHandler.class),
serverPacketHandlerSpec = @SidedPacketHandler(channels = {"ShadowCraft"}, packetHandler = ServerPacketHandler.class))
public class ShadowCraft {
	
	@Instance("ShadowCraft")
	public static ShadowCraft instance = new ShadowCraft();
	
	public static String itemsPng = "/ForgeTutorials/Buckets/items.png";
    public static String terrainPng = "/ForgeTutorials/Buckets/terrain.png";
    
	public static Block liquidShadowStill;
	public static Block liquidShadowMoving;
	public static Block shadowCatcher;
	public static Block shadowRefinery;
	
	public static Item obsidianBucket;
	public static Item shadowBucket;
	public static Item shadowBucket2;
	public static Item shadowIngot;
	public static Item shadowHelmet;
	public static Item shadowChestplate;
	public static Item shadowLeggings;
	public static Item shadowBoots;
	public static Item shadowSword;
	public static Item shadowShovel;
	public static Item shadowPickaxe;
	public static Item shadowAxe;
	
	public static int obsidianBucketID;
	public static int shadowBucketID;
	public static int shadowIngotID;
	public static int shadowHelmetID;
	public static int shadowChestplateID;
	public static int shadowLeggingsID;
	public static int shadowBootsID;
	public static int shadowSwordID;
	public static int shadowShovelID;
	public static int shadowPickaxeID;
	public static int shadowAxeID;
	
	public static int liquidShadowMovingID;
	public static int liquidShadowStillID;
	public static int shadowCatcherID;
	public static int shadowRefineryID;
	
	public static int shadowArmorParticles;
	
	public static int shadowCatcherOutputMultiplier;
	
	
	public static Logger scLog = Logger.getLogger("ShadowCraft");
	
	
	@SidedProxy(clientSide = "shadowcraft.client.ClientProxySC", serverSide = "mod.jackbeepee.common.CommonProxySC")
    public static ClientProxySC proxy;
	private GuiHandler guiHandler = new GuiHandler();
	
	static EnumArmorMaterial shadowArmorMaterial = EnumHelper.addArmorMaterial("Shadow", 35, new int[]{5, 10, 8, 7}, 20);
	static EnumToolMaterial shadowToolMaterial = EnumHelper.addToolMaterial("Shadow", 3, 800, 14.0F, 4, 20);
	
	@PreInit
	public void preLoad(FMLPreInitializationEvent event){
		scLog.setParent(FMLLog.getLogger());
		
		scLog.info("Loading/creating config");
		loadConfig(event);
	}
	
	@Init
	public void load(FMLInitializationEvent event){
		
		NetworkRegistry.instance().registerGuiHandler(this, guiHandler);
		
		GameRegistry.registerTileEntity(TEShadowCatcher.class, "tileEntityShadowCatcher");
		
		proxy.registerRenderThings();
 
		/*
		 * Add Blocks
		 */
		scLog.info("Adding blocks");
		addBlocks();
		
		/*
		 * Add Items
		 */
		scLog.info("Adding items");
        addItems();
        
        /*
         * Add Recipes
         */
		scLog.info("Adding recipes");
		addRecipes();
		
		/*
		 * Add Buildcraft Liquids
		 */
		scLog.info("Adding BC liquids...");
		LiquidManager.liquids.add(new LiquidData(new LiquidStack(liquidShadowStill, LiquidManager.BUCKET_VOLUME), new LiquidStack(liquidShadowMoving, LiquidManager.BUCKET_VOLUME), new ItemStack(shadowBucket), new ItemStack(obsidianBucket)));

		scLog.info("Registering tick handler");
		TickRegistry.registerTickHandler(new ClientTickHandler(shadowHelmet, shadowChestplate, shadowLeggings, shadowBoots), Side.CLIENT);
	}
	
	public void loadConfig(FMLPreInitializationEvent event){
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		
		obsidianBucketID = config.get(config.CATEGORY_ITEM, "Obsidian Bucket", 144).getInt(144);
		shadowBucketID = config.get(config.CATEGORY_ITEM, "Shadow Bucket", 145).getInt(145);
		shadowIngotID = config.get(config.CATEGORY_ITEM, "Shadow Ingot", 146).getInt(146);
		shadowHelmetID = config.get(config.CATEGORY_ITEM, "Shadow Helmet", 147).getInt(147);
		shadowChestplateID = config.get(config.CATEGORY_ITEM, "Shadow Chestplate", 148).getInt(148);
		shadowLeggingsID = config.get(config.CATEGORY_ITEM, "Shadow Leggings", 149).getInt(149);
		shadowBootsID = config.get(config.CATEGORY_ITEM, "Shadow Boots", 150).getInt(150);
		shadowSwordID = config.get(config.CATEGORY_ITEM, "Shadow Sword", 151).getInt(151);
		shadowShovelID = config.get(config.CATEGORY_ITEM, "Shadow Shovel", 152).getInt(152);
		shadowPickaxeID = config.get(config.CATEGORY_ITEM, "Shadow Pickaxe", 153).getInt(153);
		shadowAxeID = config.get(config.CATEGORY_ITEM, "Shadow Axe", 154).getInt(154);


		
		liquidShadowMovingID = config.get(config.CATEGORY_BLOCK, "Flowing Liquid Shadow", 139).getInt(139);
		liquidShadowStillID = config.get(config.CATEGORY_BLOCK, "Still Liquid Shadow", 140).getInt(140);
		shadowCatcherID = config.get(config.CATEGORY_BLOCK, "Shadow Catcher", 141).getInt(141);
		shadowRefineryID = config.get(config.CATEGORY_BLOCK, "Shadow Refiner", 142).getInt(142);

		
		config.get(config.CATEGORY_GENERAL, "Shadow Armor Particles", 2).comment = "Particle effects for shadow armor. 0 = off, 1 = decreased, 2 = normal, 3 = maximum";
		shadowArmorParticles = config.get(config.CATEGORY_GENERAL, "Shadow Armor Particles", 2).getInt(2);
		
		config.get(config.CATEGORY_GENERAL, "Shadow Catcher Output", 1).comment = "Amount of liquid produced by shadow catcher. 1 = normal, 2 = 2x normal, 3 = 3x normal, etc.";
		shadowCatcherOutputMultiplier = config.get(config.CATEGORY_GENERAL, "Shadow Catcher Output", 1).getInt(1);
		
		config.save();
	}
	
	public void addBlocks(){
		
		shadowCatcher = new ShadowCatcher(shadowCatcherID).setHardness(5.0F).setResistance(2000.0F);
		GameRegistry.registerBlock(shadowCatcher);
		LanguageRegistry.addName(shadowCatcher, "Shadow Catcher");
		
		liquidShadowMoving = new FlowingShadow(liquidShadowMovingID, Material.water).setHardness(100.0F).setLightOpacity(3).setBlockName("liquidshadow");
		GameRegistry.registerBlock(liquidShadowMoving);
		LanguageRegistry.addName(liquidShadowMoving, "Liquid Shadow");
		
		liquidShadowStill = new StillShadow(liquidShadowStillID, Material.water).setHardness(100.0F).setLightOpacity(3).setBlockName("liquidshadow");
		GameRegistry.registerBlock(liquidShadowStill);
		LanguageRegistry.addName(liquidShadowStill, "Liquid Shadow");
		
		shadowRefinery = new ShadowRefinery(shadowRefineryID).setHardness(5.0F).setResistance(2000.0F);
		GameRegistry.registerBlock(shadowRefinery);
		LanguageRegistry.addName(shadowRefinery, "Shadow Refinery");
		
	}
	
	public void addItems(){
		obsidianBucket = new ShadowBucket(obsidianBucketID, 0).setIconIndex(0).setItemName("obsidianBucket");
        LanguageRegistry.addName(obsidianBucket, "Obsidian Bucket");
        
        shadowBucket = new ShadowBucket(shadowBucketID, liquidShadowMoving.blockID).setIconIndex(1).setItemName("shadowBucket");
        LanguageRegistry.addName(shadowBucket, "Shadow Bucket");
        
        shadowIngot = new ShadowIngot(146).setIconIndex(3).setItemName("shadowIngot");
        LanguageRegistry.addName(shadowIngot, "Shadow Ingot");
        
        shadowHelmet = new ShadowArmor(shadowHelmetID, shadowArmorMaterial, ModLoader.addArmor("Shadow"), 0).setIconIndex(15).setItemName("shadowHelmet");
        LanguageRegistry.addName(shadowHelmet, "Shadow Helmet");
        
        shadowChestplate = new ShadowArmor(shadowChestplateID, shadowArmorMaterial, ModLoader.addArmor("Shadow"), 1).setIconIndex((16 * 1) + 15).setItemName("shadowChestplate");
        LanguageRegistry.addName(shadowChestplate, "Shadow Chestplate");
        
        shadowLeggings = new ShadowArmor(shadowLeggingsID, shadowArmorMaterial, ModLoader.addArmor("Shadow"), 2).setIconIndex((16 * 2) + 15).setItemName("shadowLeggings");
        LanguageRegistry.addName(shadowLeggings, "Shadow Leggings");
        
        shadowBoots = new ShadowArmor(shadowBootsID, shadowArmorMaterial, ModLoader.addArmor("Shadow"), 3).setIconIndex((16 * 3) + 15).setItemName("shadowBoots");
        LanguageRegistry.addName(shadowBoots, "Shadow Boots");
        
        shadowSword = new ShadowSword(shadowSwordID, shadowToolMaterial).setIconIndex((16 * 4) + 15).setItemName("shadowSword");
        shadowSword.setTextureFile("/gui/scitemtex.png");
        LanguageRegistry.addName(shadowSword, "Death's Blade");
        
        shadowShovel = new ShadowShovel(shadowShovelID, shadowToolMaterial).setIconIndex((16 * 5) + 15).setItemName("shadowShovel");
        shadowShovel.setTextureFile("/gui/scitemtex.png");
        LanguageRegistry.addName(shadowShovel, "Dirt Destroyer");
        
        shadowPickaxe = new ShadowPickaxe(shadowPickaxeID, shadowToolMaterial).setIconIndex((16 * 6) + 15).setItemName("shadowPickaxe");
        shadowPickaxe.setTextureFile("/gui/scitemtex.png");
        LanguageRegistry.addName(shadowPickaxe, "Dark Nullifier");
        
        shadowAxe = new ShadowAxe(shadowAxeID, shadowToolMaterial).setIconIndex((16 * 7) + 15).setItemName("shadowAxe");
        shadowAxe.setTextureFile("/gui/scitemtex.png");
        LanguageRegistry.addName(shadowAxe, "Nature's Nightmare");
	}
	
	public void addRecipes(){
		GameRegistry.addRecipe(new ItemStack(obsidianBucket, 1), new Object[] {"# #", " # ", '#', Block.obsidian});

		// Temp recipe
		GameRegistry.addRecipe(new ItemStack(shadowIngot, 1), new Object[] {"###", "###", "###", '#', Block.obsidian});

		GameRegistry.addRecipe(new ItemStack(shadowHelmet, 1), new Object[] {"###", "# #", '#', shadowIngot});
		GameRegistry.addRecipe(new ItemStack(shadowChestplate, 1), new Object[] {"# #", "###", "###", '#', shadowIngot});
		GameRegistry.addRecipe(new ItemStack(shadowLeggings, 1), new Object[] {"###", "# #", "# #", '#', shadowIngot});
		GameRegistry.addRecipe(new ItemStack(shadowBoots, 1), new Object[] {"# #", "# #", '#', shadowIngot});
		GameRegistry.addRecipe(new ItemStack(shadowSword, 1), new Object[] {" # ", " # ", " O ", '#', shadowIngot, 'O', Block.obsidian});
		GameRegistry.addRecipe(new ItemStack(shadowShovel, 1), new Object[] {"#", "O", "O", '#', shadowIngot, 'O', Block.obsidian});
		GameRegistry.addRecipe(new ItemStack(shadowPickaxe, 1), new Object[] {"###", " O ", " O ", '#', shadowIngot, 'O', Block.obsidian});
		GameRegistry.addRecipe(new ItemStack(shadowAxe, 1), new Object[] {"##", "#O", " O", '#', shadowIngot, 'O', Block.obsidian});

	}
}