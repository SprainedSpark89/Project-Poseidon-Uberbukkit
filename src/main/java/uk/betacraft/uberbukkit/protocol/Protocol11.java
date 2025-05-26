package uk.betacraft.uberbukkit.protocol;

import uk.betacraft.uberbukkit.Uberbukkit;

public class Protocol11 extends Protocol13 {

    @Override
    public boolean canReceiveBlockItem(int id) {
        switch (id) {
            case 31: // tallgrass
            case 358: // map
            case 96: // trapdoor
                return Uberbukkit.getPrereleaseStatus();
            case 32: // dead bush
            	return false;
            default:
                return super.canReceiveBlockItem(id);
        }
    }

    @Override
    public boolean canReceivePacket(int id) {
        switch (id) {
            case 61: // jukebox and effects
            case 131: // map packet
                return false;
            default:
                return super.canReceivePacket(id);
        }
    }
}
