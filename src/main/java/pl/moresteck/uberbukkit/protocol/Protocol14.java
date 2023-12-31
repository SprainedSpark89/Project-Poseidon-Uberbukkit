package pl.moresteck.uberbukkit.protocol;

public class Protocol14 extends Protocol2000 {

	@Override
	public boolean canReceivePacket(int id) {
		return id != 62 && id != 63;
	}
}
