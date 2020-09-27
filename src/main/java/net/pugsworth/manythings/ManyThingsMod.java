package net.pugsworth.manythings;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.pugsworth.manythings.block.ModBlocks;
import net.pugsworth.manythings.config.ConfigHelper;
import net.pugsworth.manythings.config.ManyThingsConfig;
import net.pugsworth.manythings.entity.ModEntities;
import net.pugsworth.manythings.item.ModItems;

public class ManyThingsMod implements ModInitializer {
	public static final String MODID = "many-things";
	public static final Logger logger = LogManager.getLogger(MODID);
	public static final ManyThingsConfig CONFIG = AutoConfig.register(ManyThingsConfig.class, JanksonConfigSerializer::new).getConfig();
	public static final ConfigHelper CONFIG_HELPER = new ConfigHelper(CONFIG);

	@Override
	public void onInitialize()
	{
		if (!CONFIG.enable)
			return;

		ModBlocks.RegisterBlocks();
		ModItems.RegisterItems();
		ModEntities.RegisterEntities();
	}
}
