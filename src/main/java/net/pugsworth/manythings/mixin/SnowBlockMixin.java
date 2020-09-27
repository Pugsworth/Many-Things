package net.pugsworth.manythings.mixin;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.block.Block;
import net.minecraft.block.SnowBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pugsworth.manythings.ManyThingsMod;

@Mixin(SnowBlock.class)
public class SnowBlockMixin extends Block {
    public SnowBlockMixin(Settings block$Settings_1) {
        super(block$Settings_1);
    }

    @Override
    public void onLandedUpon(World world, BlockPos blockPos, Entity entity, float fallDistance)
    {
        if (ManyThingsMod.CONFIG.isAllowed(ManyThingsMod.CONFIG.enableFallDamageTweaks)) {
            entity.handleFallDamage(fallDistance, 0.5F);
        }
        else {
            super.onLandedUpon(world, blockPos, entity, fallDistance);
        }
    }
}
