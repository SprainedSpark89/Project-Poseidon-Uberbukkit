package pl.moresteck.uberbukkit.protocol;

public class Protocol6 extends Protocol7 {

    @Override
    public boolean canReceivePacket(int id) {
        switch (id) {
            case 100: // inventory stuff -
            case 101:
            case 102:
            case 103:
            case 104:
            case 105:
            case 106: // - inventory stuff
            case 130: // sign edit
                return false;
            default:
                return super.canReceivePacket(id);
        }
    }
}
