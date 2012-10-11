package shadowcraft.shadow.item;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumMovingObjectType;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.World;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import shadowcraft.ShadowCraftShadow;

public class ItemShadowBucket extends Item{
	/** field for checking if the bucket has been filled. */
	private final int isFull;

	public ItemShadowBucket(final int par1, final int par2){
		super(par1);
		this.maxStackSize = 1;
		this.isFull = par2;
		this.setCreativeTab(CreativeTabs.tabMisc);
	}

	@Override
	public String getTextureFile(){
		return "/gui/scitemtex.png";
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is
	 * pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ItemStack onItemRightClick(final ItemStack par1ItemStack, final World par2World,
		final EntityPlayer par3EntityPlayer){
		final float var4 = 1.0F;
		final double var5 = par3EntityPlayer.prevPosX + ((par3EntityPlayer.posX - par3EntityPlayer.prevPosX) * var4);
		final double var7 = (par3EntityPlayer.prevPosY + ((par3EntityPlayer.posY - par3EntityPlayer.prevPosY) * var4) + 1.62D)
			- par3EntityPlayer.yOffset;
		final double var9 = par3EntityPlayer.prevPosZ + ((par3EntityPlayer.posZ - par3EntityPlayer.prevPosZ) * var4);
		final boolean var11 = this.isFull == 0;
		final MovingObjectPosition var12 = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, var11);

		if (var12 == null) {
			return par1ItemStack;
		} else {
			final FillBucketEvent event = new FillBucketEvent(par3EntityPlayer, par1ItemStack, par2World, var12);
			if (event.isCanceled()) {
				return par1ItemStack;
			}

			if (event.isHandeled()) {
				return event.result;
			}

			if (var12.typeOfHit == EnumMovingObjectType.TILE) {
				int var13 = var12.blockX;
				int var14 = var12.blockY;
				int var15 = var12.blockZ;

				if (!par2World.canMineBlock(par3EntityPlayer, var13, var14, var15)) {
					return par1ItemStack;
				}

				if (this.isFull == 0) {
					if (!par3EntityPlayer.canPlayerEdit(var13, var14, var15)) {
						return par1ItemStack;
					}

					if (par2World.getBlockId(var13, var14, var15) == ShadowCraftShadow.liquidShadowStill.blockID) {
						par2World.setBlockWithNotify(var13, var14, var15, 0);

						if (par3EntityPlayer.capabilities.isCreativeMode) {
							return par1ItemStack;
						}

						if (--par1ItemStack.stackSize <= 0) {
							return new ItemStack(ShadowCraftShadow.shadowBucket);
						}

						if (!par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Item.bucketWater))) {
							par3EntityPlayer.dropPlayerItem(new ItemStack(Item.bucketWater.shiftedIndex, 1, 0));
						}

						return par1ItemStack;
					}
				} else {
					if (this.isFull < 0) {
						return new ItemStack(ShadowCraftShadow.obsidianBucket);
					}

					if (var12.sideHit == 0) {
						--var14;
					}

					if (var12.sideHit == 1) {
						++var14;
					}

					if (var12.sideHit == 2) {
						--var15;
					}

					if (var12.sideHit == 3) {
						++var15;
					}

					if (var12.sideHit == 4) {
						--var13;
					}

					if (var12.sideHit == 5) {
						++var13;
					}

					if (!par3EntityPlayer.canPlayerEdit(var13, var14, var15)) {
						return par1ItemStack;
					}

					if (this.func_77875_a(par2World, var5, var7, var9, var13, var14, var15)
						&& !par3EntityPlayer.capabilities.isCreativeMode) {
						return new ItemStack(ShadowCraftShadow.obsidianBucket);
					}
				}
			}

			return par1ItemStack;
		}
	}

	public boolean func_77875_a(final World par1World, final double par2, final double par4, final double par6,
		final int par8, final int par9, final int par10){
		if (this.isFull <= 0) {
			return false;
		} else if (!par1World.isAirBlock(par8, par9, par10) && par1World.getBlockMaterial(par8, par9, par10).isSolid()) {
			return false;
		} else {

			par1World.setBlockWithNotify(par8, par9, par10, ShadowCraftShadow.liquidShadowStill.blockID);

			return true;
		}
	}
}
