package net.pugsworth.manythings.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.pugsworth.manythings.ManyThingsMod;

public class ModItems
{
    public static Item ARROW_EXPLODING = new ArrowExplodingItem(new Item.Settings().group(ItemGroup.COMBAT).maxCount(16));
    public static Item DYNAMITE_STICK = new DynamiteStickItem(new Item.Settings().group(ItemGroup.MISC).maxCount(16));
    public static Item MAGNET = new MagnetItem(new Item.Settings().group(ItemGroup.MISC).maxCount(1));

    public static void RegisterItems()
    {
        if (ManyThingsMod.CONFIG.isAllowed(ManyThingsMod.CONFIG.enableExplodingArrow))
        {
            registerItem("arrow_exploding", ARROW_EXPLODING);
            registerItem("dynamite_stick", DYNAMITE_STICK);
        }

        if (ManyThingsMod.CONFIG.isAllowed(ManyThingsMod.CONFIG.enableMagnet))
        {
            registerItem("magnet", MAGNET);
        }
    }

    public static void registerItem(String id, Item item)
	{
		Registry.register(Registry.ITEM, new Identifier(ManyThingsMod.MODID, id), item);
	}
}
