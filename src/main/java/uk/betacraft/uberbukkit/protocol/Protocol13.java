package uk.betacraft.uberbukkit.protocol;

import uk.betacraft.uberbukkit.Uberbukkit;

public class Protocol13 extends Protocol14 {

    @Override
    public boolean canReceiveBlockItem(int id) {
        switch (id) {
            case 29: // piston
            case 33: // sticky piston
            case 34: // piston extension
            case 36: // piston moving piece
            case 359: // shears
                return Uberbukkit.getPrereleaseStatus();
            default:
                return super.canReceiveBlockItem(id);
        }
    }
}
