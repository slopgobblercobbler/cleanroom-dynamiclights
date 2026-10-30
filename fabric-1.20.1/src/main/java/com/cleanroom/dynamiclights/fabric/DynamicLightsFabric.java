package com.cleanroom.dynamiclights.fabric;

import com.cleanroom.dynamiclights.DynamicLightsCore;

public class DynamicLightsFabric {
    private final DynamicLightsCore core = new DynamicLightsCore();

    public void onInitialize() {
        System.out.println("[CleanRoom Fabric] Bootstrapping " + DynamicLightsCore.MOD_NAME);
        core.onInitializeCommon();
    }
}
