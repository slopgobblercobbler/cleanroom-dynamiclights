package com.cleanroom.dynamiclights;

import com.cleanroom.common.api.CommonModInitializer;
import com.cleanroom.common.telemetry.CleanRoomTelemetry;

public class DynamicLightsCore implements CommonModInitializer {
    public static final String MOD_ID = "cleanroom_dynamiclights";
    public static final String MOD_NAME = "Dynamic Lights CleanRoom";

    private static DynamicLightsCore instance;
    private final DynamicLightsConfig config = new DynamicLightsConfig();
    private final DynamicLightsEngine engine = new DynamicLightsEngine(config);

    public static DynamicLightsCore getInstance() {
        return instance;
    }

    public DynamicLightsConfig getConfig() {
        return config;
    }

    public DynamicLightsEngine getEngine() {
        return engine;
    }

    @Override
    public String getModId() {
        return MOD_ID;
    }

    @Override
    public void onInitializeCommon() {
        instance = this;
        System.out.println("[CleanRoom] Initialized core logic for " + MOD_NAME + " (" + MOD_ID + ")");
        CleanRoomTelemetry.record("INIT", "DynamicLights client-side engine initialized (zero world churn)");
    }
}
