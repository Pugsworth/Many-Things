package net.pugsworth.manythings.entity.projectile;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion.DestructionType;
import net.pugsworth.manythings.item.ModItems;

public class ArrowExplodingEntity extends ProjectileEntity {

    public ArrowExplodingEntity(EntityType<? extends ArrowExplodingEntity> entityType_1, World world_1) {
        super(entityType_1, world_1);
        playSound();
     }

    public ArrowExplodingEntity(World world_1, double double_1, double double_2, double double_3) {
        super(EntityType.ARROW, double_1, double_2, double_3, world_1);
        playSound();
     }

    public ArrowExplodingEntity(World world_1, LivingEntity livingEntity_1) {
        super(EntityType.ARROW, livingEntity_1, world_1);
        playSound();
     }

    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(ModItems.ARROW_EXPLODING);
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);

        Vec3d pos = hitResult.getPos();
        BlockPos hitBlockPos = new BlockPos(pos);
        BlockState hitBlockState = this.world.getBlockState(hitBlockPos);

        if (this.inGround) {
            this.world.createExplosion(this, DamageSource.explosion((LivingEntity) this.getOwner()), pos.getX(), pos.getY(), pos.getZ(), 8.0f, false, DestructionType.BREAK);
            this.remove();
        }
    }

    @Override
    protected void onHit(LivingEntity livingEntity_1) {
        // TODO Auto-generated method stub
        super.onHit(livingEntity_1);
    }

    private void playSound()
    {
        this.world.playSoundFromEntity(null, this, SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0f, 1.0f);
    }
    
}
