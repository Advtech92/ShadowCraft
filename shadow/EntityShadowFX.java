package shadowcraft.shadow;

import net.minecraft.src.EntityFX;
import net.minecraft.src.Tessellator;
import net.minecraft.src.World;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityShadowFX extends EntityFX{
	float reddustParticleScale;

	public EntityShadowFX(final World par1World, final double par2, final double par4, final double par6,
		final float par8, final float par9, final float par10){
		this(par1World, par2, par4, par6, 1.0F, par8, par9, par10);
	}

	public EntityShadowFX(final World par1World, final double par2, final double par4, final double par6,
		final float par8, float par9, final float par10, final float par11){
		super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
		this.motionX *= 0.10000000149011612D;
		this.motionY *= 0.10000000149011612D;
		this.motionZ *= 0.10000000149011612D;

		if (par9 == 0.0F) {
			par9 = 1.0F;
		}

		final float var12 = ((float) Math.random() * 0.4F) + 0.6F;
		this.particleRed = 0.9F;
		this.particleGreen = 0.9F;
		this.particleBlue = 1.0F;
		this.particleScale *= 0.75F;
		this.particleScale *= par8;
		this.reddustParticleScale = this.particleScale;
		this.particleMaxAge = (int) (8.0D / ((Math.random() * 0.8D) + 0.2D));
		this.particleMaxAge = (int) (this.particleMaxAge * par8);
		this.noClip = false;
	}

	@Override
	public void renderParticle(final Tessellator par1Tessellator, final float par2, final float par3, final float par4,
		final float par5, final float par6, final float par7){
		float var8 = ((this.particleAge + par2) / this.particleMaxAge) * 32.0F;

		if (var8 < 0.0F) {
			var8 = 0.0F;
		}

		if (var8 > 1.0F) {
			var8 = 1.0F;
		}

		this.particleScale = this.reddustParticleScale * var8;
		super.renderParticle(par1Tessellator, par2, par3, par4, par5, par6, par7);
	}

	@Override
	public void onUpdate(){
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		if (this.particleAge++ >= this.particleMaxAge) {
			this.setDead();
		}

		this.setParticleTextureIndex(7 - ((this.particleAge * 8) / this.particleMaxAge));
		this.moveEntity(this.motionX, this.motionY, this.motionZ);

		if (this.posY == this.prevPosY) {
			this.motionX *= 1.1D;
			this.motionZ *= 1.1D;
		}

		this.motionX *= 0.9599999785423279D;
		this.motionY *= 0.9599999785423279D;
		this.motionZ *= 0.9599999785423279D;

		if (this.onGround) {
			this.motionX *= 0.699999988079071D;
			this.motionZ *= 0.699999988079071D;
		}
	}
}
