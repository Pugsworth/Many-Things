package net.pugsworth.manythings.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.pugsworth.manythings.ManyThingsMod;

@Mixin(SignBlockEntity.class)
public class EditSignMixin
{
    @Shadow
    private boolean editable;

    @Inject(method = "onActivate", at = @At("HEAD"))
    public void onActivate(PlayerEntity player, CallbackInfoReturnable<Boolean> callback)
    {
        if (ManyThingsMod.CONFIG.isAllowed(ManyThingsMod.CONFIG.enableEditSigns))
        {
            ItemStack handItem = player.getStackInHand(player.getActiveHand());
            Identifier itemid = Registry.ITEM.getId(handItem.getItem());
            Identifier configitemid = new Identifier(ManyThingsMod.CONFIG.editSignItemId);

            boolean useEmptyHand = ManyThingsMod.CONFIG.editSignItemId.equals("") && handItem.isEmpty();
            boolean sneakConfigCheck = ManyThingsMod.CONFIG.isAllowed(ManyThingsMod.CONFIG.editSignRequireSneak);
            boolean handCheck = ((sneakConfigCheck || useEmptyHand) || itemid.equals(configitemid));
            boolean sneakCheck = (sneakConfigCheck && player.isSneaking()) || (!sneakConfigCheck && !player.isSneaking()) ;

            if (player.abilities.allowModifyWorld && handCheck && sneakCheck)
            {
                editable = true;
                SignBlockEntity sign = (SignBlockEntity) (Object) this;
                player.openEditSignScreen(sign);
            }
        }
    }
}
