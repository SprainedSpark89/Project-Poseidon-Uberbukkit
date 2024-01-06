package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet7UseEntity extends Packet {

    public int a;
    public int target;
    public int c;

    public Packet7UseEntity() {
    }

    public void a(DataInputStream datainputstream) throws IOException {
        this.a = datainputstream.readInt();
        this.target = datainputstream.readInt();
        // uberbukkit
        if (this.pvn >= 5)
            this.c = datainputstream.readByte();
        else
            this.c = 0;
    }

    public void a(DataOutputStream dataoutputstream) throws IOException {
        dataoutputstream.writeInt(this.a);
        dataoutputstream.writeInt(this.target);
        // uberbukkit
        if (this.pvn >= 5)
            dataoutputstream.writeByte(this.c);
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 8 + (this.pvn >= 5 ? 1 : 0); // uberbukkit
    }
}
