package net.pugsworth.manythings;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.pugsworth.manythings.block.ModBlocks;
import net.pugsworth.manythings.config.ConfigHelper;
import net.pugsworth.manythings.config.ManyThingsConfig;
import net.pugsworth.manythings.enchantment.ModEnchantments;
import net.pugsworth.manythings.entity.ModEntities;
import net.pugsworth.manythings.item.ModItems;

public class ManyThingsMod implements ModInitializer {
	public static final String MODID = "many-things";
	public static final Logger logger = LogManager.getLogger(MODID);
	public static final ManyThingsConfig CONFIG = AutoConfig.register(ManyThingsConfig.class, JanksonConfigSerializer::new).getConfig();
	public static final ConfigHelper CONFIG_HELPER = new ConfigHelper(CONFIG);

	public static class Tags {
		public static Tag<Item> CROP_ITEMS;
		public static Tag<Item> SEED_ITEMS;
		public static Tag<Block> CROP_BLOCKS;
	}

	@Override
	public void onInitialize() {
		if (!CONFIG.enable)
			return;

		Identifier cropsID = new Identifier(MODID, "crops");
		Identifier seedID = new Identifier(MODID, "seeds");
		Tags.CROP_ITEMS = TagRegistry.item(cropsID);
		Tags.CROP_BLOCKS = TagRegistry.block(cropsID);
		Tags.SEED_ITEMS = TagRegistry.item(seedID);

		ModBlocks.RegisterBlocks();
		ModItems.RegisterItems();
		ModEntities.RegisterEntities();
		ModEnchantments.RegisterEnchantments();
	}
}


/**
 * TODO:
 *  Update README
 *  Make magnet work continuous by holding
 */