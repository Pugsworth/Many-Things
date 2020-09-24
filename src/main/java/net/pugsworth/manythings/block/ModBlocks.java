package net.pugsworth.manythings.block;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.pugsworth.manythings.ManyThingsMod;

public class ModBlocks
{

    
    public static void RegisterBlocks()
    {

    }

    public static void registerBlock(String id, Block block, ItemGroup group)
	{
		Registry.register(Registry.BLOCK, new Identifier(ManyThingsMod.MODID, id), block);
		Registry.register(Registry.ITEM, new Identifier(ManyThingsMod.MODID, id), new BlockItem(block, new Item.Settings().group(group)));
	}
}
