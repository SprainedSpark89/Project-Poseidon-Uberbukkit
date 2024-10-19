package uk.betacraft.uberbukkit.protocol;

public class Protocol5 extends Protocol6 {

    @Override
    public boolean canReceivePacket(int id) {
        switch (id) {
            case 60: // explosion
                return false;
            default:
                return super.canReceivePacket(id);
        }
    }
}
