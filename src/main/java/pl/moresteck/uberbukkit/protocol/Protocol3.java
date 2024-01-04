package pl.moresteck.uberbukkit.protocol;

public class Protocol3 extends Protocol4 {

    @Override
    public boolean canReceivePacket(int id) {
        switch (id) {
            case 7: // use entity
            case 28: // entity velocity
            case 39: // entity attach
                return false;
            default:
                return super.canReceivePacket(id);
        }
    }
}
