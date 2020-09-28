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

    @Comment("Enables exploding arrows and dynamite")
    public boolean enableExplodingArrow = true;

    @Comment("Enable tweaks to fall damage through reduced damage when landing on certain blocks.")
    public boolean enableFallDamageTweaks = true;

    @Comment("Allow edit signs via right-click.")
    public boolean enableEditSigns = true;
    @Comment("The item held to edit signs. Set to blank to edit with anything.")
    public String editSignItemId = "minecraft:ink_sac";
    @Comment("Require crouch to edit signs. Due to the nature of crouching with items, this option only works with an empty hand.")
    public boolean editSignRequireSneak = false;

    @Comment("Enable item magnet.")
    public boolean enableMagnet = true;
    @Comment("Range of influence on items")
    public int magnetRange = 16;

    /**
     * Helper method to check a boolean value with 'enable'
     * If enable is false, always return false, otherwise, return value
     * @param value
     * @return value && enable
     */
    public boolean isAllowed(boolean value)
    {
        return value && enable;
    }
}
