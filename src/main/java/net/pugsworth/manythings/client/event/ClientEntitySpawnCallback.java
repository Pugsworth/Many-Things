package net.pugsworth.manythings.client.event;

import java.util.ArrayList;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.packet.EntitySpawnS2CPacket;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;

public class ClientEntitySpawnCallback {
	public static final ArrayList<ClientEntitySpawnHook> HOOKS = new ArrayList<>();
	public interface ClientEntitySpawnHook {
		public Entity getEntity(EntitySpawnS2CPacket packet, ClientPlayerEntity player, ClientWorld world);
	}
}
