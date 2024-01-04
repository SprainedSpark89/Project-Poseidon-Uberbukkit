package pl.moresteck.uberbukkit.protocol;

public class Protocol13 extends Protocol14 {

    @Override
    public boolean canReceiveBlockItem(int id) {
        switch (id) {
            case 29: // piston
            case 33: // sticky piston
            case 34: // piston extension
            case 36: // piston moving piece
            case 359: // shears
                return false;
            default:
                return super.canReceiveBlockItem(id);
        }
    }
}
