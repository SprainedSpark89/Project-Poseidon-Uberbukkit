package uk.betacraft.uberbukkit.protocol;

import org.bukkit.entity.Squid;

public class Protocol7 extends Protocol8 {

    @Override
    public boolean canReceiveBlockItem(int id) {
        switch (id) {
            case 354: // cake
            case 353: // sugar
            case 352: // bone
            case 351: // dye
            case 21: // lapis ore
            case 22: // lapis block
            case 23: // dispenser
            case 24: // sandstone
            case 25: // noteblock
                return false;
            default:
                return super.canReceiveBlockItem(id);
        }
    }

    @Override
    public boolean canReceivePacket(int id) {
        switch (id) {
            case 19: // entity action
            case 25: // painting
            case 40: // entity metadata
            case 54: // play noteblock
                return false;
            default:
                return super.canReceivePacket(id);
        }
    }

    @Override
    public boolean canSeeMob(Class<?> claz) {
        if (Squid.class.isAssignableFrom(claz)) return false;

        return super.canSeeMob(claz);
    }
}
