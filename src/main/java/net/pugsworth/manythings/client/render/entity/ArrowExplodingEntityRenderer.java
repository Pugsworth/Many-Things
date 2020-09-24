package net.pugsworth.manythings.client.render.entity;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;
import net.pugsworth.manythings.ManyThingsMod;
import net.pugsworth.manythings.entity.projectile.ArrowExplodingEntity;

public class ArrowExplodingEntityRenderer extends ProjectileEntityRenderer<ArrowExplodingEntity> {

    public static final Identifier SKIN = new Identifier(ManyThingsMod.MODID, "textures/entity/projectiles/arrow.png");

    public ArrowExplodingEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher);
    }

    @Override
    protected Identifier getTexture(ArrowExplodingEntity entity) {
        return SKIN;
    }
}
