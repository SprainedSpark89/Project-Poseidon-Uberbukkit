package uk.betacraft.uberbukkit.protocol;

public class Protocol10 extends Protocol11 {

    @Override
    public boolean canReceiveBlockItem(int id) {
        switch (id) {
            case 30: // cobweb
            case 27: // powered rails
            case 28: // detector rails
                return false;
            default:
                return super.canReceiveBlockItem(id);
        }
    }

    @Override
    public boolean canReceivePacket(int id) {
        switch (id) {
            case 200: // statistics
            case 71: // weather
                return false;
            default:
                return super.canReceivePacket(id);
        }
    }
}
