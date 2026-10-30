package com.cleanroom.dynamiclights;

import com.cleanroom.common.math.BlockVec3i;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class DynamicLightsTest {

    private static class MockLightWorld implements DynamicLightsWorldView {
        private final Map<BlockVec3i, Integer> ambientLight = new HashMap<>();
        private final Map<BlockVec3i, String> blocks = new HashMap<>();
        private final List<DynamicLightSource> entities = new ArrayList<>();
        private final List<DynamicLightSource> droppedItems = new ArrayList<>();

        @Override
        public int getBlockLight(BlockVec3i pos) {
            return ambientLight.getOrDefault(pos, 0);
        }

        @Override
        public String getBlockId(BlockVec3i pos) {
            return blocks.getOrDefault(pos, "minecraft:air");
        }

        @Override
        public List<DynamicLightSource> getNearbyEntities(BlockVec3i center, double radius) {
            return entities;
        }

        @Override
        public List<DynamicLightSource> getNearbyDroppedItems(BlockVec3i center, double radius) {
            return droppedItems;
        }
    }

    private static class MockLightPlayer implements DynamicLightsPlayerView {
        double x = 0.0;
        double y = 64.0;
        double z = 0.0;
        String mainHand;
        String offHand;
        String helmet;

        @Override public double getX() { return x; }
        @Override public double getY() { return y; }
        @Override public double getZ() { return z; }
        @Override public String getMainHandItemId() { return mainHand; }
        @Override public String getOffHandItemId() { return offHand; }
        @Override public String getHelmetItemId() { return helmet; }
    }

    private MockLightWorld world;
    private MockLightPlayer player;
    private DynamicLightsEngine engine;

    @BeforeEach
    void setUp() {
        world = new MockLightWorld();
        player = new MockLightPlayer();
        engine = new DynamicLightsEngine();
    }

    @Test
    void testHandheldTorchIlluminatesVicinity() {
        player.x = 0.0;
        player.y = 64.0;
        player.z = 0.0;
        player.mainHand = "minecraft:torch";

        // Target at distance 2 blocks away: (0, 64, 2)
        BlockVec3i targetPos = new BlockVec3i(0, 64, 2);
        int light = engine.computeLightAt(world, player, targetPos);

        // Torch = 14, dist = 2 -> 14 - 2 = 12
        assertEquals(12, light);
    }

    @Test
    void testOffhandLanternLuminance() {
        player.x = 10.0;
        player.y = 64.0;
        player.z = 10.0;
        player.offHand = "minecraft:lantern";

        // Target at distance 1 block away: (10, 64, 11)
        BlockVec3i targetPos = new BlockVec3i(10, 64, 11);
        int light = engine.computeLightAt(world, player, targetPos);

        // Lantern = 15, dist = 1 -> 15 - 1 = 14
        assertEquals(14, light);
    }

    @Test
    void testAttenuationDropsToZeroBeyondRange() {
        player.x = 0.0;
        player.y = 64.0;
        player.z = 0.0;
        player.mainHand = "minecraft:torch"; // light = 14

        // Target at distance 16 blocks away
        BlockVec3i targetPos = new BlockVec3i(0, 64, 16);
        int light = engine.computeLightAt(world, player, targetPos);

        assertEquals(0, light);
    }

    @Test
    void testBurningEntityIlluminatesEnvironment() {
        player.x = 100.0;
        player.y = 64.0;
        player.z = 100.0; // Far away

        // Burning entity at (5.0, 64.0, 5.0)
        world.entities.add(new DynamicLightSource(5.0, 64.0, 5.0, LuminanceRegistry.getEntityLuminance("minecraft:zombie", true)));

        // Target at (5, 64, 6) -> 1 block away
        BlockVec3i targetPos = new BlockVec3i(5, 64, 6);
        int light = engine.computeLightAt(world, player, targetPos);

        // Burning = 15, dist = 1 -> 15 - 1 = 14
        assertEquals(14, light);
    }

    @Test
    void testServerBlockstateImmutabilityInvariant() {
        player.x = 0.0;
        player.y = 64.0;
        player.z = 0.0;
        player.mainHand = "minecraft:torch";

        BlockVec3i targetPos = new BlockVec3i(0, 64, 2);
        int computed = engine.computeLightAt(world, player, targetPos);
        assertEquals(12, computed);

        // Server world state MUST REMAIN UNMODIFIED
        assertEquals(0, world.getBlockLight(targetPos));
        assertEquals("minecraft:air", world.getBlockId(targetPos));
    }
}
