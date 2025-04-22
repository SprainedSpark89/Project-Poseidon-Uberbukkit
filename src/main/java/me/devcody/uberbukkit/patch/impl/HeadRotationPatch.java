package me.devcody.uberbukkit.patch.impl;

import me.devcody.uberbukkit.patch.Patch;
import net.minecraft.server.EntityPlayer;

public class HeadRotationPatch extends Patch {
    public HeadRotationPatch() {
        super("head-rotation");
    }

    @Override
    protected boolean validate(EntityPlayer player, Object... data) {
        if (data.length < 2) return true;

        if (!(data[0] instanceof Float && data[1] instanceof Float)) return true;

        float yaw = (float) data[0];
        float pitch = (float) data[1];

        return (pitch >= -90 && pitch <= 90)
            && (yaw >= -180 && yaw <= 180);
    }
}
