package com.cleanroom.dynamiclights;

import com.cleanroom.common.math.BlockVec3i;
import java.util.List;

public interface DynamicLightsWorldView {
    int getBlockLight(BlockVec3i pos);
    String getBlockId(BlockVec3i pos);
    List<DynamicLightSource> getNearbyEntities(BlockVec3i center, double radius);
    List<DynamicLightSource> getNearbyDroppedItems(BlockVec3i center, double radius);
}
