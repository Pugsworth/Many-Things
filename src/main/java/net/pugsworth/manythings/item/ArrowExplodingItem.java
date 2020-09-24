package net.pugsworth.manythings.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.pugsworth.manythings.ManyThingsMod;
import net.pugsworth.manythings.entity.projectile.ArrowExplodingEntity;

public class ArrowExplodingItem extends ArrowItem {

    public ArrowExplodingItem(Item.Settings item$Settings) {
        super(item$Settings);
    }

    @Override
    public ProjectileEntity createArrow(World world, ItemStack itemStack, LivingEntity livingEntity) {
        ManyThingsMod.logger.info("ArrowExplodingEntity createArrow");
        ArrowExplodingEntity arrowEntity = new ArrowExplodingEntity(world, livingEntity);
        return arrowEntity;
    }
}
