package net.pugsworth.manythings.client;

import java.util.UUID;

import net.minecraft.client.network.packet.EntitySpawnS2CPacket;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.MathHelper;

public class EntityCreationStruct {
    public Object entity;
    public int id;
    public UUID uuid;
    public double x;
    public double y;
    public double z;
    public int velocityX;
    public int velocityY;
    public int velocityZ;
    public int pitch;
    public int yaw;
    public EntityType<?> entityTypeId;
    public int entityData;

    public EntityCreationStruct(Object entity, EntitySpawnS2CPacket packet) {
        this.entity = entity;
        this.entityTypeId = packet.getEntityTypeId();
        this.id = packet.getId();
        this.uuid = packet.getUuid();
        this.x = packet.getX();
        this.y = packet.getY();
        this.z = packet.getZ();
        this.velocityX = (int)(MathHelper.clamp(packet.getVelocityX(), -3.9D, 3.9D) * 8000.0D);
        this.velocityY = (int)(MathHelper.clamp(packet.getVelocityY(), -3.9D, 3.9D) * 8000.0D);
        this.velocityZ = (int)(MathHelper.clamp(packet.getVelocityz(), -3.9D, 3.9D) * 8000.0D);
        this.pitch = MathHelper.floor(packet.getPitch() * 256.0F / 360.0F);
        this.yaw = MathHelper.floor(packet.getYaw() * 256.0F / 360.0F);
        this.entityData = packet.getEntityData();
    }

    public void createEntity(ClientWorld world)
    {
        Entity entity = (Entity)this.entity;
        if (this.entity != null) {
            entity.updateTrackedPosition(this.x, this.y, this.z);
            entity.pitch = (float)(this.pitch * 360) / 256.0F;
            entity.yaw = (float)(this.yaw * 360) / 256.0F;
            entity.setEntityId(this.id);
            entity.setUuid(this.uuid);
            world.addEntity(this.id, (Entity)this.entity);
        }
    }
}