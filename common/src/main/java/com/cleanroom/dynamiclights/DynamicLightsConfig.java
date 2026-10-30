package com.cleanroom.dynamiclights;

import com.cleanroom.common.config.JsonConfigProvider;
import java.nio.file.Path;

public class DynamicLightsConfig {
    public boolean enabled = true;
    public int updateFrequency = 2;
    public boolean enablePlayerLighting = true;
    public boolean enableEntityLighting = true;
    public boolean enableDroppedItemLighting = true;
    public int maxLightDistance = 16;

    public static DynamicLightsConfig load(Path configPath) {
        return JsonConfigProvider.loadConfig(configPath, DynamicLightsConfig.class, new DynamicLightsConfig());
    }

    public boolean save(Path configPath) {
        return JsonConfigProvider.saveConfig(configPath, this);
    }
}
