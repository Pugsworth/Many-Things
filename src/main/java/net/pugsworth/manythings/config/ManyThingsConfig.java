package net.pugsworth.manythings.config;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;

import net.pugsworth.manythings.ManyThingsMod;

@Config(name=ManyThingsMod.MODID)
public class ManyThingsConfig implements ConfigData
{
    @Comment("Enable the mod's functionality.")
    public boolean enable = true;

    @Comment("The item held to edit signs. Set to blank to edit with anything.")
    public String editSignItemId = "minecraft:ink_sac";

}
