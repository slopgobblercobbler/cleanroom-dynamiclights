package com.cleanroom.dynamiclights;

import java.util.HashMap;
import java.util.Map;

public final class LuminanceRegistry {
    private static final Map<String, Integer> ITEM_LUMINANCE = new HashMap<>();
    private static final Map<String, Integer> ENTITY_LUMINANCE = new HashMap<>();

    static {
        // Items
        ITEM_LUMINANCE.put("minecraft:torch", 14);
        ITEM_LUMINANCE.put("minecraft:lantern", 15);
        ITEM_LUMINANCE.put("minecraft:soul_torch", 10);
        ITEM_LUMINANCE.put("minecraft:soul_lantern", 10);
        ITEM_LUMINANCE.put("minecraft:glowstone", 15);
        ITEM_LUMINANCE.put("minecraft:sea_lantern", 15);
        ITEM_LUMINANCE.put("minecraft:lava_bucket", 15);
        ITEM_LUMINANCE.put("minecraft:campfire", 15);
        ITEM_LUMINANCE.put("minecraft:soul_campfire", 10);
        ITEM_LUMINANCE.put("minecraft:shroomlight", 15);
        ITEM_LUMINANCE.put("minecraft:ochre_froglight", 15);
        ITEM_LUMINANCE.put("minecraft:pearlescent_froglight", 15);
        ITEM_LUMINANCE.put("minecraft:verdant_froglight", 15);
        ITEM_LUMINANCE.put("minecraft:redstone_torch", 7);
        ITEM_LUMINANCE.put("minecraft:spectral_arrow", 8);

        // Entities
        ENTITY_LUMINANCE.put("minecraft:blaze", 12);
        ENTITY_LUMINANCE.put("minecraft:magma_cube", 10);
        ENTITY_LUMINANCE.put("minecraft:glow_squid", 11);
    }

    private LuminanceRegistry() {}

    public static int getItemLuminance(String itemId) {
        if (itemId == null) return 0;
        return ITEM_LUMINANCE.getOrDefault(itemId, 0);
    }

    public static int getEntityLuminance(String entityType, boolean isOnFire) {
        if (isOnFire) {
            return 15;
        }
        if (entityType == null) return 0;
        return ENTITY_LUMINANCE.getOrDefault(entityType, 0);
    }
}
