package net.pugsworth.manythings.config;

import java.util.function.Function;

import io.github.prospector.modmenu.api.ModMenuApi;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.pugsworth.manythings.ManyThingsMod;

@Environment(EnvType.CLIENT)
public class ManyThingsConfigMenu implements ModMenuApi
{

    @Override
    public String getModId() {
        return ManyThingsMod.MODID;
    }

    @Override
    public Function<Screen, ? extends Screen> getConfigScreenFactory() {
        return parent -> AutoConfig.getConfigScreen(ManyThingsConfig.class, parent).get();
    }
    
}
