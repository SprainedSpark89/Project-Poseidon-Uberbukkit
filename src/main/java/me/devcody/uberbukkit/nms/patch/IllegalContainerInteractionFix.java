package me.devcody.uberbukkit.nms.patch;

import com.legacyminecraft.poseidon.PoseidonConfig;
import me.devcody.uberbukkit.util.math.Vec3i;
import net.minecraft.server.EntityPlayer;

import java.util.logging.Logger;

public class IllegalContainerInteractionFix {
    public static final int MAX_DISTANCE = PoseidonConfig.getInstance().getInt("fix.illegal-container-interaction.max-distance", 4);
    public static final boolean LOG_VIOLATION = PoseidonConfig.getInstance().getBoolean("fix.illegal-container-interaction.log-violation", false);

    public static boolean checkForViolations(Vec3i containerPosition, EntityPlayer player) {
        if (containerPosition == null || player == null) {
            return false;
        }

        containerPosition = containerPosition.abs();
        Vec3i playerPosition = new Vec3i((int) player.locX, (int) player.locY, (int) player.locZ).abs();
        Vec3i distance = containerPosition.subtract(playerPosition).abs();
        if (distance.getX() > MAX_DISTANCE || distance.getY() > MAX_DISTANCE || distance.getZ() > MAX_DISTANCE) {
            violate(player);
            return true;
        }

        return false;
    }

    public static void violate(EntityPlayer player) {
        if (LOG_VIOLATION) {
            Logger.getLogger("UberBukkit").warning(String.format("%1$s tried to interact with a container too far away from them!", player.name));
        }
        player.y();
    }
}
