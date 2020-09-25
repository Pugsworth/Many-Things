package net.pugsworth.manythings.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.packet.EntitySpawnS2CPacket;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.pugsworth.manythings.client.event.ClientEntitySpawnCallback;
import net.pugsworth.manythings.client.event.ClientEntitySpawnCallback.ClientEntitySpawnHook;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {

    @Shadow
    public ClientWorld world;

    @Shadow public MinecraftClient client;

    @Inject(method="onEntitySpawn",at=@At("TAIL"))
    public void onEntitySpawn(EntitySpawnS2CPacket packet, CallbackInfo info) {
        for (ClientEntitySpawnHook hook : ClientEntitySpawnCallback.HOOKS) {
            Entity entity = hook.getEntity(packet, client.player, world);

            if (entity != null) {
                entity.updateTrackedPosition(packet.getX(), packet.getY(), packet.getZ());
                entity.pitch = (float)(packet.getPitch() * 360) / 256.0F;
                entity.yaw = (float)(packet.getYaw() * 360) / 256.0F;
                entity.setEntityId(packet.getId());
                entity.setUuid(packet.getUuid());
                world.addEntity(packet.getId(), entity);
            }
        }
    }
}
