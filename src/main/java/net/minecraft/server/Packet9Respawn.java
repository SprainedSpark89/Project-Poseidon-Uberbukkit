package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet9Respawn extends Packet {

    public byte a;
    public long seed; // uberbukkit

    public Packet9Respawn() {
    }

    public Packet9Respawn(byte b0, long seed) {
        this.a = b0;
        this.seed = seed; // uberbukkit
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public void a(DataInputStream datainputstream) throws IOException {
        // uberbukkit
        if (this.pvn >= 12) {
            this.a = datainputstream.readByte();
        } else {
            this.a = 0;
        }

        if (this.pvn >= 2000) {
            this.seed = datainputstream.readLong();
        } else {
            this.seed = -1L;
        }
    }

    public void a(DataOutputStream dataoutputstream) throws IOException {
        // uberbukkit
        if (this.pvn >= 12) {
            dataoutputstream.writeByte(this.a);
        }

        if (this.pvn >= 2000) {
            dataoutputstream.writeLong(this.seed);
        }
    }

    public int a() {
        // uberbukkit
        return (this.pvn >= 12 ? 1 : 0) + (this.pvn >= 2000 ? 8 : 0);
    }
}
