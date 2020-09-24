package net.pugsworth.manythings.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.pugsworth.manythings.entity.thrown.ThrownDynamiteStickEntity;

public class DynamiteStickItem extends Item {

    public DynamiteStickItem(Settings item$Settings_1) {
        super(item$Settings_1);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        ItemStack itemStack = playerEntity.getStackInHand(hand);
        if (!playerEntity.abilities.creativeMode) {
           itemStack.decrement(1);
        }
  
        world.playSound((PlayerEntity)null, playerEntity.x, playerEntity.y, playerEntity.z, SoundEvents.ENTITY_EGG_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (RANDOM.nextFloat() * 0.4F + 0.8F));
        world.playSound((PlayerEntity)null, playerEntity.x, playerEntity.y, playerEntity.z, SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.PLAYERS, 0.5F, 0.4F / (RANDOM.nextFloat() * 0.4F + 0.8F));

        if (!world.isClient) {
           ThrownDynamiteStickEntity thrownEntity = new ThrownDynamiteStickEntity(world, playerEntity);
           thrownEntity.setItem(itemStack);
           thrownEntity.method_19207(playerEntity, playerEntity.pitch, playerEntity.yaw, 0.0F, 1.5F, 1.0F);
           world.spawnEntity(thrownEntity);
        }
  
        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
     }
}
