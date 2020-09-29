package net.pugsworth.manythings.mixin;

import org.spongepowered.asm.mixin.Mixin;

import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.block.Fertilizable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ToolItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pugsworth.manythings.ManyThingsMod;

@Mixin(ToolItem.class)
public class ToolItemMixin extends Item {

    public ToolItemMixin(Settings item$Settings_1) {
		super(item$Settings_1);
	}

	@Override
    public boolean canMine(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity) {
        boolean result = true;

        if (FabricToolTags.HOES.contains((Item)(Object)this))
        {
            Block block = blockState.getBlock();
            if (ManyThingsMod.Tags.CROP_BLOCKS.contains(block))
            {
                if (block instanceof CropBlock)
                {
                    result = ((CropBlock) block).isMature(blockState);
                }
                else if (block instanceof Fertilizable) {
                    result = !((Fertilizable) block).isFertilizable(null, blockPos, blockState, false);
                }
            }
        }

        return result;
    }
}
