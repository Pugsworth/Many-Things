package net.pugsworth.manythings.entity;

import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.pugsworth.manythings.ManyThingsMod;
import net.pugsworth.manythings.entity.projectile.ArrowExplodingEntity;
import net.pugsworth.manythings.entity.thrown.ThrownDynamiteStickEntity;

public class ModEntities {
    public static EntityType<ArrowExplodingEntity> ARROW_EXPLODING_ENTITY;
    public static EntityType<ThrownDynamiteStickEntity> THROWN_DYNAMITE_STICK_ENTITY;

    public static void RegisterEntities()
    {
        ARROW_EXPLODING_ENTITY = registerEntity("arrow_exploding_entity", FabricEntityTypeBuilder.<ArrowExplodingEntity>create(EntityCategory.MISC, ArrowExplodingEntity::new).size(EntityDimensions.fixed(0.5f, 0.5f)).build());
        THROWN_DYNAMITE_STICK_ENTITY = registerEntity("thrown_dynamite_stick_entity", FabricEntityTypeBuilder.<ThrownDynamiteStickEntity>create(EntityCategory.MISC, ThrownDynamiteStickEntity::new).size(EntityDimensions.fixed(0.25f, 0.25f)).build());
    }

    public static <T extends Entity> EntityType<T> registerEntity(String id, EntityType<T> entityType)
    {
        return Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(ManyThingsMod.MODID, id),
            entityType
        );
    }
}
