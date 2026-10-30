package com.cleanroom.dynamiclights;

import com.cleanroom.common.math.BlockVec3i;
import java.util.List;

public class DynamicLightsEngine {
    private final DynamicLightsConfig config;

    public DynamicLightsEngine(DynamicLightsConfig config) {
        this.config = config;
    }

    public DynamicLightsEngine() {
        this(new DynamicLightsConfig());
    }

    public DynamicLightsConfig getConfig() {
        return config;
    }

    public int getLightAttenuation(int sourceLuminance, double distance) {
        if (sourceLuminance <= 0) return 0;
        return Math.max(0, sourceLuminance - (int) Math.floor(distance));
    }

    public int computeLightAt(
            DynamicLightsWorldView world,
            DynamicLightsPlayerView player,
            BlockVec3i targetPos
    ) {
        if (!config.enabled) {
            return world.getBlockLight(targetPos);
        }

        int maxDynamicLight = 0;

        // 1. Player handheld & equipped luminance
        if (config.enablePlayerLighting && player != null) {
            int mainLight = LuminanceRegistry.getItemLuminance(player.getMainHandItemId());
            int offLight = LuminanceRegistry.getItemLuminance(player.getOffHandItemId());
            int helmLight = LuminanceRegistry.getItemLuminance(player.getHelmetItemId());
            int playerLight = Math.max(mainLight, Math.max(offLight, helmLight));

            if (playerLight > 0) {
                double dx = targetPos.x() - player.getX();
                double dy = targetPos.y() - player.getY();
                double dz = targetPos.z() - player.getZ();
                double dist = Math.sqrt(dx * dx + dy * dy + dz * dz);

                int dyn = getLightAttenuation(playerLight, dist);
                if (dyn > maxDynamicLight) {
                    maxDynamicLight = dyn;
                }
            }
        }

        // 2. Nearby entities luminance (burning entities, blazes, magma cubes, glow squids)
        if (config.enableEntityLighting && world != null) {
            List<DynamicLightSource> entities = world.getNearbyEntities(targetPos, config.maxLightDistance);
            if (entities != null) {
                for (DynamicLightSource src : entities) {
                    double dx = targetPos.x() - src.x();
                    double dy = targetPos.y() - src.y();
                    double dz = targetPos.z() - src.z();
                    double dist = Math.sqrt(dx * dx + dy * dy + dz * dz);

                    int dyn = getLightAttenuation(src.luminance(), dist);
                    if (dyn > maxDynamicLight) {
                        maxDynamicLight = dyn;
                    }
                }
            }
        }

        // 3. Nearby dropped items luminance
        if (config.enableDroppedItemLighting && world != null) {
            List<DynamicLightSource> items = world.getNearbyDroppedItems(targetPos, config.maxLightDistance);
            if (items != null) {
                for (DynamicLightSource src : items) {
                    double dx = targetPos.x() - src.x();
                    double dy = targetPos.y() - src.y();
                    double dz = targetPos.z() - src.z();
                    double dist = Math.sqrt(dx * dx + dy * dy + dz * dz);

                    int dyn = getLightAttenuation(src.luminance(), dist);
                    if (dyn > maxDynamicLight) {
                        maxDynamicLight = dyn;
                    }
                }
            }
        }

        // Invariant: Zero server world churn. Combine ambient light without modifying world blockstates.
        int ambient = world != null ? world.getBlockLight(targetPos) : 0;
        return Math.max(ambient, maxDynamicLight);
    }
}
