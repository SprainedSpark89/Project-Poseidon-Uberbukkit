package me.devcody.uberbukkit.patch.impl;

import me.devcody.uberbukkit.patch.Patch;
import me.devcody.uberbukkit.math.Vec3i;
import net.minecraft.server.EntityPlayer;

public class ContainerDistancePatch extends Patch {
    private final int maxDistance;

    public ContainerDistancePatch() {
        super("container-distance");
        this.maxDistance = (int) getSetting("max-distance", 4);
    }

    @Override
    public boolean validate(EntityPlayer player, Object... data) {
        if (!(data[0] instanceof Vec3i)) return true;

        Vec3i containerPos = ((Vec3i) data[0]).abs();
        Vec3i playerPos = new Vec3i((int) player.locX, (int) player.locY, (int) player.locZ).abs();
        Vec3i distance = containerPos.subtract(playerPos).abs();

        return distance.getX() <= maxDistance && distance.getY() <= maxDistance && distance.getZ() <= maxDistance;
    }

    @Override
    public void fail(EntityPlayer player) {
        super.fail(player);
        player.y();
    }
}
