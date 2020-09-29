package net.pugsworth.manythings.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.annotation.Nullable;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

@Mixin(BoneMealItem.class)
public class BoneMealItemMixin {
    
    @Inject(method = "useOnGround", at = @At(value = "RETURN", ordinal = 1))
    private static boolean useOnGround(ItemStack itemStack, World world, BlockPos blockPos, @Nullable Direction direction, CallbackInfoReturnable<Boolean> cir)
    {
        BlockPos affectedPos = blockPos.offset(direction.getOpposite());
        BlockState blockState = world.getBlockState(affectedPos);
        if (blockState.getBlock().equals(Blocks.DIRT))
        {
            if (!world.isClient && world.random.nextInt(4) == 0)
            {
                world.setBlockState(affectedPos, Blocks.GRASS_BLOCK.getDefaultState());
                world.playLevelEvent(2005, blockPos, 0);
            }

            itemStack.decrement(1);
            
            return true;
        }

        return false;
    }
}
