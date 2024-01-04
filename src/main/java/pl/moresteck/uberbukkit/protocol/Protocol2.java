package pl.moresteck.uberbukkit.protocol;

import org.bukkit.entity.Ghast;
import org.bukkit.entity.PigZombie;

public class Protocol2 extends Protocol3 {

    @Override
    public boolean canReceiveBlockItem(int id) {
        switch (id) {
            case 91: // lit pumpkin
            case 90: // portal
            case 86: // pumpkin
            case 89: // glowstone block
            case 88: // soul sand
            case 87: // netherrack
            case 350: // cooked fish
            case 349: // raw fish
            case 348: // glowstone dust
            case 347: // clock
                return false;
            default:
                return super.canReceiveBlockItem(id);
        }
    }

    @Override
    public boolean canSeeMob(Class<?> claz) {
        if (Ghast.class.isAssignableFrom(claz) || PigZombie.class.isAssignableFrom(claz))
            return false;

        return super.canSeeMob(claz);
    }
}
