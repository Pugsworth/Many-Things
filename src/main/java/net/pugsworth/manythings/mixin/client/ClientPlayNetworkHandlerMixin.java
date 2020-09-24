package net.pugsworth.manythings.mixin.client;

import java.sql.Struct;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.packet.EntitySpawnS2CPacket;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.pugsworth.manythings.client.event.ClientEntitySpawnCallback;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {

    @Shadow
    public ClientWorld world;

    @Shadow public MinecraftClient client;

    @Inject(method="onEntitySpawn",at=@At("TAIL"))
    public void onEntitySpawn(EntitySpawnS2CPacket packet, CallbackInfo info) {
        EntityType<?> etypeid = packet.getEntityTypeId();

        ActionResult result = ClientEntitySpawnCallback.EVENT.invoker().onEntitySpawn(world, client.player, packet);

        if (result == ActionResult.FAIL) {
            info.cancel();
        }
    }
}
