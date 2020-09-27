package net.pugsworth.manythings.config;

import java.lang.reflect.Field;

import net.pugsworth.manythings.ManyThingsMod;

public class ConfigHelper {
    private static ConfigHelper INSTANCE;
    private ManyThingsConfig config;

    public ConfigHelper(ManyThingsConfig config)
    {
        if (INSTANCE == null) {
            INSTANCE = this;
            INSTANCE.config = config;
        }
    }

    public boolean isAllowed(String configName)
    {
        boolean result = false;

        try {
            Field field = ManyThingsConfig.class.getField(configName);
            result = field.getBoolean(config);
            result = result && ManyThingsMod.CONFIG.enable;

        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            ManyThingsMod.logger.trace("Invalid field %s", configName);
        }
        
        return result;
    }

    public static boolean isAllowedStatic(String configName)
    {
        return INSTANCE.isAllowed(configName);
    }

    public ConfigHelper getInstance() {
        return INSTANCE;
    }
}
