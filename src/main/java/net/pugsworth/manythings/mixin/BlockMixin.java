package net.pugsworth.manythings.mixin;

import java.lang.reflect.Field;
import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.annotation.Nullable;
import net.fabricmc.fabric.api.tag.FabricItemTags;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pugsworth.manythings.ManyThingsMod;
import net.pugsworth.manythings.enchantment.HarvesterEnchantment;
import net.pugsworth.manythings.enchantment.ModEnchantments;

@Mixin(Block.class)
public class BlockMixin {

    // itemStack = item used to break
    @Overwrite
    public static void dropStacks(BlockState blockState, World world, BlockPos blockPos,
            @Nullable BlockEntity blockEntity, Entity entity, ItemStack itemStack) {
        if (world instanceof ServerWorld) {

            Block block = blockState.getBlock();
            Item tool = itemStack.getItem();
            Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(itemStack);

            // ManyThingsMod.logger.info("itemStack: {}, blockState: {}", itemStack,
            // blockState);

            Block.getDroppedStacks(blockState, (ServerWorld) world, blockPos, blockEntity, entity, itemStack)
                    .forEach((droppedItemStack) -> {
                        Item item = droppedItemStack.getItem();
                        ManyThingsMod.logger.info("dropped item: {} qty: {}", item, droppedItemStack.getCount());
                        // ManyThingsMod.logger.info("is hoe: {}, has seed: {}", FabricToolTags.HOES.contains(tool), ManyThingsMod.Tags.SEED_ITEMS.contains(item));
                        // ManyThingsMod.logger.info("tags.seeds: {}", ManyThingsMod.Tags.SEED_ITEMS);

                        if (FabricToolTags.HOES.contains(tool) && ManyThingsMod.Tags.SEED_ITEMS.contains(item)
                                && enchantments.containsKey(ModEnchantments.HARVESTER)) {
                            // ManyThingsMod.logger.info("Harvester activated!");

                            try {
                                Field ageField = block.getClass().getField("AGE");
                                if (ageField != null) {
                                    droppedItemStack.decrement(1);
                                    world.setBlockState(blockPos, blockState.with((IntProperty)ageField.get(block), 0));
                                }

                            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
                                e.printStackTrace();
                                droppedItemStack.increment(1); // reset because we just decremented
                            }
                        }

                        Block.dropStack(world, blockPos, droppedItemStack);
                    });
        }

        blockState.onStacksDropped(world, blockPos, itemStack);
    }
}
