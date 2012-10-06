package shadowcraft;

import java.util.logging.Logger;

import buildcraft.api.liquids.LiquidData;
import buildcraft.api.liquids.LiquidManager;
import buildcraft.api.liquids.LiquidStack;

import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EnumArmorMaterial;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.ModLoader;
import net.minecraft.src.TileEntityRenderer;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import shadowcraft.core.ClientPacketHandler;
import shadowcraft.core.ServerPacketHandler;
import shadowcraft.shadow.ClientProxy;
import shadowcraft.shadow.ClientTickHandler;
import shadowcraft.shadow.CommonProxy;
import shadowcraft.shadow.ModelShadowCube;
import shadowcraft.shadow.RenderShadowCube;
import shadowcraft.shadow.block.BlockDarkOre;
import shadowcraft.shadow.block.BlockFlowingShadow;
import shadowcraft.shadow.block.BlockShadowCatcher;
import shadowcraft.shadow.block.BlockShadowCraft;
import shadowcraft.shadow.block.BlockShadowRefinery;
import shadowcraft.shadow.block.BlockStillShadow;
import shadowcraft.shadow.item.ItemShadowArmor;
import shadowcraft.shadow.item.ItemShadowAxe;
import shadowcraft.shadow.item.ItemShadowBucket;
import shadowcraft.shadow.item.ItemShadowCraft;
import shadowcraft.shadow.item.ItemShadowIngot;
import shadowcraft.shadow.item.ItemShadowPickaxe;
import shadowcraft.shadow.item.ItemShadowShovel;
import shadowcraft.shadow.item.ItemShadowSword;
import shadowcraft.shadow.tileentity.TileEntityShadowCatcher;
import shadowcraft.shadow.tileentity.TileEntityShadowRefinery;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;

@Mod(modid = "ShadowCraft|Shadow", name = "ShadowCraft Shadow", version = "0.0")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class ShadowCraftShadow {

	@Instance("ShadowCraft|Shadow")
	public static ShadowCraftShadow instance = new ShadowCraftShadow();
	
    
	public static Block liquidShadowStill;
	public static Block liquidShadowMoving;
	public static Block shadowCatcher;
	public static Block shadowRefinery;
	public static Block darkOre;
	public static Block shadowBlock;
	
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
	public static Item shadowCrystal;
	
	public static int liquidShadowMovingID;
	public static int liquidShadowStillID;
	public static int shadowCatcherID;
	public static int shadowRefineryID;
	public static int darkOreID;
	public static int shadowBlockID;
	
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
	public static int shadowCrystalID;
	
	public static int shadowArmorParticles;
	
	public static int shadowCatcherOutputMultiplier;
	
	
	public static EnumArmorMaterial shadowArmorMaterial = EnumHelper.addArmorMaterial("Shadow", 35, new int[]{5, 10, 8, 7}, 20);
	public static EnumToolMaterial shadowToolMaterial = EnumHelper.addToolMaterial("Shadow", 3, 800, 14.0F, 4, 20);
	
	public static Logger scLog = Logger.getLogger("ShadowCraft|Shadow");
	
	@SidedProxy(clientSide = "shadowcraft.shadow.ClientProxy", serverSide = "shadowcraft.shadow.CommonProxy")
	public static ClientProxy clientProxy;
	
	@PreInit
	public void preLoad(FMLPreInitializationEvent event){
		scLog.setParent(FMLLog.getLogger());
		
		scLog.info("Loading/creating config");
		loadConfig(event);
	}
	
	@Init
	public void load(FMLInitializationEvent event){
		if(!Loader.isModLoaded("ShadowCraft Core")){
			scLog.severe("All ShadowCraft mods must be installed");
		}
		
		clientProxy.preLoadTextures();
		
		addBlocks();
		
		addItems();
		
		addRecipes();
		
		GameRegistry.registerTileEntity(TileEntityShadowCatcher.class, "tileEntityShadowCatcher");
		GameRegistry.registerTileEntity(TileEntityShadowRefinery.class, "tileEntityShadowRefinery");
		
		TileEntityRenderer.instance.specialRendererMap.put(TileEntityShadowRefinery.class, new RenderShadowCube(new ModelShadowCube()));
		
		LiquidManager.liquids.add(new LiquidData(new LiquidStack(liquidShadowStill, LiquidManager.BUCKET_VOLUME), new LiquidStack(liquidShadowMoving, LiquidManager.BUCKET_VOLUME), new ItemStack(shadowBucket), new ItemStack(obsidianBucket)));

		TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
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
		shadowCrystalID = config.get(config.CATEGORY_ITEM, "Caliginous Crystal", 155).getInt(155);

		
		liquidShadowMovingID = config.get(config.CATEGORY_BLOCK, "Flowing Liquid Shadow", 139).getInt(139);
		liquidShadowStillID = config.get(config.CATEGORY_BLOCK, "Still Liquid Shadow", 140).getInt(140);
		shadowCatcherID = config.get(config.CATEGORY_BLOCK, "Shadow Catcher", 141).getInt(141);
		shadowRefineryID = config.get(config.CATEGORY_BLOCK, "Shadow Refinery", 142).getInt(142);
		darkOreID = config.get(config.CATEGORY_BLOCK, "Dark Ore", 143).getInt(143);
		shadowBlockID = config.get(config.CATEGORY_BLOCK, "Cryptic Block", 144).getInt(144);
		
		
		config.get(config.CATEGORY_GENERAL, "Shadow Armor Particles", 2).comment = "Particle effects for shadow armor. 0 = off, 1 = decreased, 2 = normal, 3 = maximum";
		shadowArmorParticles = config.get(config.CATEGORY_GENERAL, "Shadow Armor Particles", 2).getInt(2);
		
		config.get(config.CATEGORY_GENERAL, "Shadow Catcher Output", 1).comment = "Amount of liquid produced by shadow catcher. 1 = normal, 2 = 2x normal, 3 = 3x normal, etc.";
		shadowCatcherOutputMultiplier = config.get(config.CATEGORY_GENERAL, "Shadow Catcher Output", 1).getInt(1);
		
		config.save();
	}
	
	public void addBlocks(){
		shadowCatcher = new BlockShadowCatcher(shadowCatcherID).setHardness(5.0F).setResistance(2000.0F);
		GameRegistry.registerBlock(shadowCatcher);
		LanguageRegistry.addName(shadowCatcher, "Shadow Catcher");
		
		liquidShadowMoving = new BlockFlowingShadow(liquidShadowMovingID, Material.water).setHardness(100.0F).setLightOpacity(3).setBlockName("liquidshadow");
		GameRegistry.registerBlock(liquidShadowMoving);
		LanguageRegistry.addName(liquidShadowMoving, "Liquid Shadow");
		
		liquidShadowStill = new BlockStillShadow(liquidShadowStillID, Material.water).setHardness(100.0F).setLightOpacity(3).setBlockName("liquidshadow");
		GameRegistry.registerBlock(liquidShadowStill);
		LanguageRegistry.addName(liquidShadowStill, "Liquid Shadow");
		
		shadowRefinery = new BlockShadowRefinery(shadowRefineryID).setHardness(5.0F).setResistance(2000.0F);
		GameRegistry.registerBlock(shadowRefinery);
		LanguageRegistry.addName(shadowRefinery, "Shadow Refinery");
		
		darkOre = new BlockDarkOre(darkOreID).setHardness(10.0F).setResistance(500.0F);
		GameRegistry.registerBlock(darkOre);
		LanguageRegistry.addName(darkOre, "Dark Ore");
		
		shadowBlock = new BlockShadowCraft(shadowBlockID, Material.rock).setHardness(10.0F).setResistance(500.0F).setBlockName("shadowBlock");
		shadowBlock.blockIndexInTexture = 16 + 6;
		GameRegistry.registerBlock(shadowBlock);
		LanguageRegistry.addName(shadowBlock, "Cryptic Block");
	}
	
	public void addItems(){
		obsidianBucket = new ItemShadowBucket(obsidianBucketID, 0).setIconIndex(0).setItemName("obsidianBucket");
        LanguageRegistry.addName(obsidianBucket, "Obsidian Bucket");
        
        shadowBucket = new ItemShadowBucket(shadowBucketID, liquidShadowMoving.blockID).setIconIndex(1).setItemName("shadowBucket");
        LanguageRegistry.addName(shadowBucket, "Shadow Bucket");
        
        shadowIngot = new ItemShadowIngot(146).setIconIndex(3).setItemName("shadowIngot");
        LanguageRegistry.addName(shadowIngot, "Shadow Ingot");
        
        shadowHelmet = new ItemShadowArmor(shadowHelmetID, shadowArmorMaterial, ModLoader.addArmor("Shadow"), 0).setIconIndex(15).setItemName("shadowHelmet");
        LanguageRegistry.addName(shadowHelmet, "Shadow Helmet");
        
        shadowChestplate = new ItemShadowArmor(shadowChestplateID, shadowArmorMaterial, ModLoader.addArmor("Shadow"), 1).setIconIndex((16 * 1) + 15).setItemName("shadowChestplate");
        LanguageRegistry.addName(shadowChestplate, "Shadow Chestplate");
        
        shadowLeggings = new ItemShadowArmor(shadowLeggingsID, shadowArmorMaterial, ModLoader.addArmor("Shadow"), 2).setIconIndex((16 * 2) + 15).setItemName("shadowLeggings");
        LanguageRegistry.addName(shadowLeggings, "Shadow Leggings");
        
        shadowBoots = new ItemShadowArmor(shadowBootsID, shadowArmorMaterial, ModLoader.addArmor("Shadow"), 3).setIconIndex((16 * 3) + 15).setItemName("shadowBoots");
        LanguageRegistry.addName(shadowBoots, "Shadow Boots");
        
        shadowSword = new ItemShadowSword(shadowSwordID, shadowToolMaterial).setIconIndex((16 * 4) + 15).setItemName("shadowSword");
        shadowSword.setTextureFile("/gui/scitemtex.png");
        LanguageRegistry.addName(shadowSword, "Death's Blade");
        
        shadowShovel = new ItemShadowShovel(shadowShovelID, shadowToolMaterial).setIconIndex((16 * 5) + 15).setItemName("shadowShovel");
        shadowShovel.setTextureFile("/gui/scitemtex.png");
        LanguageRegistry.addName(shadowShovel, "Dirt Destroyer");
        
        shadowPickaxe = new ItemShadowPickaxe(shadowPickaxeID, shadowToolMaterial).setIconIndex((16 * 6) + 15).setItemName("shadowPickaxe");
        shadowPickaxe.setTextureFile("/gui/scitemtex.png");
        LanguageRegistry.addName(shadowPickaxe, "Dark Nullifier");
        
        shadowAxe = new ItemShadowAxe(shadowAxeID, shadowToolMaterial).setIconIndex((16 * 7) + 15).setItemName("shadowAxe");
        shadowAxe.setTextureFile("/gui/scitemtex.png");
        LanguageRegistry.addName(shadowAxe, "Nature's Nightmare");
        
        shadowCrystal = new ItemShadowCraft(shadowCrystalID).setIconIndex(4).setItemName("shadowCrystal").setCreativeTab(CreativeTabs.tabMaterials);
        LanguageRegistry.addName(shadowCrystal, "Caliginous Crystal");
	}
	
	public void addRecipes(){
		GameRegistry.addRecipe(new ItemStack(obsidianBucket, 1), new Object[] {"# #", " # ", '#', Block.obsidian});
		GameRegistry.addRecipe(new ItemStack(shadowHelmet, 1), new Object[] {"###", "# #", '#', shadowIngot});
		GameRegistry.addRecipe(new ItemStack(shadowChestplate, 1), new Object[] {"# #", "###", "###", '#', shadowIngot});
		GameRegistry.addRecipe(new ItemStack(shadowLeggings, 1), new Object[] {"###", "# #", "# #", '#', shadowIngot});
		GameRegistry.addRecipe(new ItemStack(shadowBoots, 1), new Object[] {"# #", "# #", '#', shadowIngot});
		GameRegistry.addRecipe(new ItemStack(shadowSword, 1), new Object[] {" # ", " # ", " O ", '#', shadowIngot, 'O', Block.obsidian});
		GameRegistry.addRecipe(new ItemStack(shadowShovel, 1), new Object[] {"#", "O", "O", '#', shadowIngot, 'O', Block.obsidian});
		GameRegistry.addRecipe(new ItemStack(shadowPickaxe, 1), new Object[] {"###", " O ", " O ", '#', shadowIngot, 'O', Block.obsidian});
		GameRegistry.addRecipe(new ItemStack(shadowAxe, 1), new Object[] {"##", "#O", " O", '#', shadowIngot, 'O', Block.obsidian});
		GameRegistry.addRecipe(new ItemStack(shadowBlock, 1), new Object[] {"##", "##", '#', shadowCrystal});
		GameRegistry.addRecipe(new ItemStack(shadowCatcher, 1), new Object[] {"###", "#L#", "###", '#', shadowBlock, 'L', Item.bucketLava});
	}

}
