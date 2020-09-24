package net.pugsworth.manythings.client.render.entity;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;
import net.pugsworth.manythings.ManyThingsMod;
import net.pugsworth.manythings.entity.projectile.ArrowExplodingEntity;

public class ThrownDynamiteStickEntityRenderer extends ProjectileEntityRenderer<ArrowExplodingEntity> {

    public static final Identifier SKIN = new Identifier(ManyThingsMod.MODID, "textures/item/dynamite_stick.png");

    public ThrownDynamiteStickEntityRenderer(EntityRenderDispatcher entityRenderDispatcher_1) {
        super(entityRenderDispatcher_1);
    }

    @Override
    protected Identifier getTexture(ArrowExplodingEntity var1) {
        return SKIN;
    }
}
