package net.pugsworth.manythings.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item.Settings;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.pugsworth.manythings.ManyThingsMod;

public class ModItems
{
    public static Item ItemTest = new Item(new Item.Settings().group(ItemGroup.MISC).maxCount(16));

    public static void RegisterItems()
    {
        registerItem("item_test", ItemTest);
    }

    public static void registerItem(String id, Item item)
	{
		Registry.register(Registry.ITEM, new Identifier(ManyThingsMod.MODID, id), item);
	}
}
