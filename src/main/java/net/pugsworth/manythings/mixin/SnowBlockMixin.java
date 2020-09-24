package net.pugsworth.manythings.mixin;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.block.Block;
import net.minecraft.block.SnowBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(SnowBlock.class)
public class SnowBlockMixin extends Block {
    public SnowBlockMixin(Settings block$Settings_1) {
        super(block$Settings_1);
    }

    @Override
    public void onLandedUpon(World world_1, BlockPos blockPos_1, Entity entity_1, float float_1)
    {
        entity_1.handleFallDamage(float_1, 0.5F);
    }
}
