package uk.betacraft.uberbukkit.protocol;

import uk.betacraft.uberbukkit.protocol.extension.Protocol2000;

public class Protocol14 extends Protocol2000 {

    @Override
    public boolean canReceivePacket(int id) {
        return id != 62 && id != 63;
    }
}
