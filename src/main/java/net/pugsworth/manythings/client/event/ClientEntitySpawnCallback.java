package net.pugsworth.manythings.client.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.packet.EntitySpawnS2CPacket;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.ActionResult;

public interface ClientEntitySpawnCallback {
    Event<ClientEntitySpawnCallback> EVENT = EventFactory.createArrayBacked(ClientEntitySpawnCallback.class,
			(listeners) -> (world, client, packet) -> {
				for (ClientEntitySpawnCallback event : listeners) {
					ActionResult result = event.onEntitySpawn(world, client, packet);

					if (result != ActionResult.PASS) {
						return result;
					}
				}

				return ActionResult.PASS;
			}
	);

    ActionResult onEntitySpawn(ClientWorld world, ClientPlayerEntity client, EntitySpawnS2CPacket packet);
}
