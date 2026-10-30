package com.cleanroom.dynamiclights.neoforge;

import com.cleanroom.dynamiclights.DynamicLightsCore;

public class DynamicLightsNeoForge {
    private final DynamicLightsCore core = new DynamicLightsCore();

    public DynamicLightsNeoForge() {
        System.out.println("[CleanRoom NeoForge] Bootstrapping " + DynamicLightsCore.MOD_NAME);
        core.onInitializeCommon();
    }
}
