package uk.betacraft.uberbukkit.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.NetHandler;
import net.minecraft.server.Packet;

public class Packet62Sound extends Packet {
    public String sound;
    public double locX;
    public double locY;
    public double locZ;
    public float volume;
    public float pitch;

    public Packet62Sound(String s, double d0, double d1, double d2, float f, float f1) {
        this.sound = s;
        this.locX = d0;
        this.locY = d1;
        this.locZ = d2;
        this.volume = f;
        this.pitch = f1;
    }

    public void a(DataInputStream datainputstream) throws IOException {}

    public void a(DataOutputStream dataoutputstream) throws IOException {
        dataoutputstream.writeUTF(this.sound);
        dataoutputstream.writeDouble(this.locX);
        dataoutputstream.writeDouble(this.locY);
        dataoutputstream.writeDouble(this.locZ);
        dataoutputstream.writeFloat(this.volume);
        dataoutputstream.writeFloat(this.pitch);
    }

    public void a(NetHandler nethandler) {
        nethandler.handle62Sound(this);
    }

    public int a() {
        return this.sound.length() + 8 * 3 + 4 * 2;
    }
}
