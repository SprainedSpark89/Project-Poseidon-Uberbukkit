package uk.betacraft.uberbukkit.protocol;

import uk.betacraft.uberbukkit.protocol.extension.*;

public interface Protocol {
    public boolean canReceiveBlockItem(int id);

    public boolean canReceivePacket(int id);

    public boolean canSeeMob(Class<?> claz);

    static Protocol getProtocolClass(int pvn) {
        switch (pvn) {
            case 2:
                return new Protocol2();
            case 3:
                return new Protocol3();
            case 4:
                return new Protocol4();
            case 5:
                return new Protocol5();
            case 6:
                return new Protocol6();
            case 7:
                return new Protocol7();
            case 8:
                return new Protocol8();
            case 9:
                return new Protocol9();
            case 10:
                return new Protocol10();
            case 11:
                return new Protocol11();
            case 13:
                return new Protocol13();
            case 14:
                return new Protocol14();
            case 1984:
                return new Protocol1984();
            case 2000:
                return new Protocol2000();
        }
        return null;
    }
}
