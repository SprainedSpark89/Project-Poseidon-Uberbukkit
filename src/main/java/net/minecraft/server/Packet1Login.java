package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet1Login extends Packet {

    public int a;
    public String name;
    public long c;
    public byte d;

    public Packet1Login() {}

    public Packet1Login(String s, int i, long j, byte b0) {
        this.name = s;
        this.a = i;
        this.c = j;
        this.d = b0;
    }

    public void a(DataInputStream datainputstream) throws IOException {
        this.a = this.pvn = datainputstream.readInt();
        // uberbukkit start
        if (this.pvn >= 11) {
            this.name = a(datainputstream, 16);
        } else {
            this.name = datainputstream.readUTF();
            datainputstream.readUTF();
        }

        if (this.pvn >= 3) {
            this.c = datainputstream.readLong();
            this.d = datainputstream.readByte();
        }
        // uberbukkit end
    }

    public void a(DataOutputStream dataoutputstream) throws IOException {
        dataoutputstream.writeInt(this.a);
        // uberbukkit
        if (this.pvn >= 11) {
            a(this.name, dataoutputstream);
        } else {
            dataoutputstream.writeUTF(this.name);
            dataoutputstream.writeUTF("");
        }

        if (this.pvn >= 3) {
            dataoutputstream.writeLong(this.c);
            dataoutputstream.writeByte(this.d);
        }
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 4 + this.name.length() + 4 + (this.pvn >= 3 ? 5 : 0); // uberbukkit
    }
}