package me.devcody.uberbukkit.patch;

import net.minecraft.server.EntityPlayer;
import uk.betacraft.uberbukkit.UberbukkitConfig;

import java.util.logging.Logger;

public abstract class Patch {
    private final String id;
    private final boolean enabled;
    private final boolean log;

    public Patch(String id) {
        this.id = id;
        this.enabled = UberbukkitConfig.getInstance().getBoolean("patch." + id + ".enabled", true);
        this.log = UberbukkitConfig.getInstance().getBoolean("patch." + id + ".log", false);
    }

    public boolean check(EntityPlayer player, Object... data) {
        if (!enabled || player == null) return true;

        if (!validate(player, data)) {
            fail(player);
            return false;
        } else {
            return true;
        }
    }

    protected abstract boolean validate(EntityPlayer player, Object ...data);

    public void fail(EntityPlayer player) {
        if (this.log) {
            Logger.getLogger("UberBukkit").warning("Check " + id + " failed for player " + player.name);
        }
    }

    protected Object getSetting(String key, Object def) {
        Object prop = UberbukkitConfig.getInstance().getProperty("patch." + id + "." + key);
        return prop == null ? def : prop;
    }
}
