package net.pugsworth.manythings.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pugsworth.manythings.ManyThingsMod;

@Mixin(Entity.class)
public class EntityMixin {
    @Shadow
    float fallDistance;

    @Shadow
    World world;
    
    @Overwrite
    public void fall(double verticalMove, boolean onGround, BlockState blockState, BlockPos blockPos)
    {
        if (onGround) {
            if (this.fallDistance > 0.0F) {
                BlockState upBlockState = world.getBlockState(blockPos.up());
                if (ManyThingsMod.CONFIG.isAllowed(ManyThingsMod.CONFIG.enableFallDamageTweaks) && upBlockState.getBlock().equals(Blocks.SNOW))
                {
                    int layers = upBlockState.get(Properties.LAYERS);
                    float reduction = 0.0f;

                    if (layers < 4) {
                        reduction = 0.1f * layers;
                    }
                    else {
                        reduction = 0.5f;
                    }

                    ((Entity)(Object) this).handleFallDamage(this.fallDistance, reduction);
                }
                else {
                    blockState.getBlock().onLandedUpon(this.world, blockPos, (Entity)(Object)this, this.fallDistance);
                }
            }

            this.fallDistance = 0.0F;
        } else if (verticalMove < 0.0D) {
            this.fallDistance = (float) ((double) this.fallDistance - verticalMove);
        }
    }
}
