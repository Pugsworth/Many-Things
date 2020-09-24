package net.pugsworth.manythings;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.render.EntityRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.ArrowEntityRenderer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ActionResult;
import net.pugsworth.manythings.client.EntityCreationStruct;
import net.pugsworth.manythings.client.event.ClientEntitySpawnCallback;
import net.pugsworth.manythings.entity.ModEntities;
import net.pugsworth.manythings.entity.projectile.ArrowExplodingEntity;
import net.pugsworth.manythings.entity.thrown.ThrownDynamiteStickEntity;
import net.pugsworth.manythings.mixin.client.ClientPlayNetworkHandlerMixin;

@Environment(EnvType.CLIENT)
public class ManyThingsClientMod implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        EntityRendererRegistry.INSTANCE.register(ArrowExplodingEntity.class, (dispatcher, context) -> new ArrowEntityRenderer(dispatcher));
        EntityRendererRegistry.INSTANCE.register(ThrownDynamiteStickEntity.class, (dispatcher, context) -> new FlyingItemEntityRenderer<ThrownDynamiteStickEntity>(dispatcher, context.getItemRenderer(), 1.0f));



        ClientEntitySpawnCallback.EVENT.register(
            (world, player, packet) -> {

                if (packet.getEntityTypeId() == ModEntities.THROWN_DYNAMITE_STICK_ENTITY)
                {
                    Entity entity = new ThrownDynamiteStickEntity(world, player);
                    EntityCreationStruct struct = new EntityCreationStruct(entity, packet);
                    struct.createEntity(world);
                }

                return ActionResult.PASS;
            }
        );
    }

}
