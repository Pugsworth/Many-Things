package net.pugsworth.manythings.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.pugsworth.manythings.enchantment.ModEnchantments;

@Mixin(Enchantment.class)
public class EnchantmentMixin {

    @Inject(method = "isAcceptableItem", at = @At("HEAD"), cancellable = true)
    private void isAcceptableItem(ItemStack itemStack, CallbackInfoReturnable<Boolean> info) {
        Enchantment enchantment = (Enchantment)(Object)this;
        Item item = itemStack.getItem();
         
        if (enchantment == ModEnchantments.HARVESTER) {
            if (FabricToolTags.HOES.contains(item)) {
                info.setReturnValue(true);
            }
        }
    }
    
}
