package me.devcody.uberbukkit.patch;

import me.devcody.uberbukkit.patch.impl.*;

public class Patches {
    public static final Patch
        CONTAINER_DISTANCE = new ContainerDistancePatch(),
        HEAD_ROTATION = new HeadRotationPatch();
}
