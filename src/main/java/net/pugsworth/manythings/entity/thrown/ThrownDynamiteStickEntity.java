package net.pugsworth.manythings.entity.thrown;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion.DestructionType;
import net.pugsworth.manythings.entity.ModEntities;
import net.pugsworth.manythings.item.ModItems;

public class ThrownDynamiteStickEntity extends ThrownItemEntity {

    public ThrownDynamiteStickEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public ThrownDynamiteStickEntity(World world, PlayerEntity playerEntity) {
        super(ModEntities.THROWN_DYNAMITE_STICK_ENTITY, playerEntity, world);
    }

	@Override
    protected Item getDefaultItem() {
        return ModItems.DYNAMITE_STICK;
    }

    @Override
    public ItemStack getStack() {
        return ModItems.DYNAMITE_STICK.getStackForRender();
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        Vec3d pos = hitResult.getPos();

        this.world.createExplosion(this, DamageSource.explosion((LivingEntity) this.getOwner()), pos.getX(), pos.getY(), pos.getZ(), 8.0f, false, DestructionType.BREAK);
        this.remove();
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return super.createSpawnPacket();
    }
}
