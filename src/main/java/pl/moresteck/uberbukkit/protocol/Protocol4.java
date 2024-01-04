package pl.moresteck.uberbukkit.protocol;

public class Protocol4 extends Protocol5 {

    @Override
    public boolean canReceivePacket(int id) {
        switch (id) {
            case 8: // health update
            case 9: // respawn
            case 38: // entity status
                return false;
            default:
                return super.canReceivePacket(id);
        }
    }
}
