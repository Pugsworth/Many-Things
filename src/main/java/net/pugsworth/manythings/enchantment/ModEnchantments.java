package net.pugsworth.manythings.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Weight;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.pugsworth.manythings.ManyThingsMod;

public class ModEnchantments {
    public static Enchantment HARVESTER = new HarvesterEnchantment(Weight.UNCOMMON, EnchantmentTarget.ALL, new EquipmentSlot[] {EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    
    public ModEnchantments() {

    }

    public static void RegisterEnchantments() {
        registerEnchantment(new Identifier(ManyThingsMod.MODID, "harvester"), HARVESTER);
    }

    private static Enchantment registerEnchantment(Identifier id, Enchantment enchantment) {
        return Registry.register(Registry.ENCHANTMENT, id, enchantment);
    }
}
