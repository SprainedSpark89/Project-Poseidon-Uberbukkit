package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet62Sound extends Packet {
	public String sound;
	public double locX;
	public double locY;
	public double locZ;
	public float f;
	public float f1;

	public Packet62Sound(String s, double d0, double d1, double d2, float f, float f1) {
		this.sound = s;
		this.locX = d0;
		this.locY = d1;
		this.locZ = d2;
		this.f = f;
		this.f1 = f1;
	}


	public void a(DataInputStream datainputstream) throws IOException {
		
	}

	public void a(DataOutputStream dataoutputstream) throws IOException {
		dataoutputstream.writeUTF(this.sound);
		dataoutputstream.writeDouble(locX);
		dataoutputstream.writeDouble(locY);
		dataoutputstream.writeDouble(locZ);
		dataoutputstream.writeFloat(f);
		dataoutputstream.writeFloat(f1);
	}

	public void a(NetHandler nethandler) {
		nethandler.handle62Sound(this);
	}

	public int a() {
		return this.sound.length() + 8 * 3 + 4 * 2;
	}
}
