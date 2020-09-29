package net.pugsworth.manythings.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class HarvesterEnchantment extends Enchantment {

    protected HarvesterEnchantment(Weight enchantment$Weight, EnchantmentTarget enchantmentTarget, EquipmentSlot[] equipmentSlots) {
        super(enchantment$Weight, enchantmentTarget, equipmentSlots);
    }

    @Override
    public int getMinimumLevel() {
        return super.getMinimumLevel();
    }

    @Override
    public int getMaximumLevel() {
        return super.getMaximumLevel();
    }
    
}
