package net.pugsworth.manythings;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.render.EntityRendererRegistry;
import net.minecraft.client.render.entity.ArrowEntityRenderer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.EntityType;
import net.pugsworth.manythings.client.event.ClientEntitySpawnCallback;
import net.pugsworth.manythings.entity.ModEntities;
import net.pugsworth.manythings.entity.projectile.ArrowExplodingEntity;
import net.pugsworth.manythings.entity.thrown.ThrownDynamiteStickEntity;

@Environment(EnvType.CLIENT)
public class ManyThingsClientMod implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        EntityRendererRegistry.INSTANCE.register(ArrowExplodingEntity.class, (dispatcher, context) -> new ArrowEntityRenderer(dispatcher));
        EntityRendererRegistry.INSTANCE.register(ThrownDynamiteStickEntity.class, (dispatcher, context) -> new FlyingItemEntityRenderer<ThrownDynamiteStickEntity>(dispatcher, context.getItemRenderer(), 1.0f));

        ClientEntitySpawnCallback.HOOKS.add((packet, player, world) -> {

            EntityType<?> id = packet.getEntityTypeId();
            
            if (id == ModEntities.THROWN_DYNAMITE_STICK_ENTITY)
            {
                return new ThrownDynamiteStickEntity(world, player); 
            }
            else if (id == ModEntities.ARROW_EXPLODING_ENTITY)
            {
                return new ArrowExplodingEntity(world, player);
            }

            return null;
        });
    }

}
