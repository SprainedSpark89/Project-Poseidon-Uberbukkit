package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet2Handshake extends Packet {

    public String a;
    public boolean pvn11;

    public Packet2Handshake() {
    }

    public Packet2Handshake(String s, boolean pvn11) {
        this.a = s;
        this.pvn11 = pvn11;
    }

    public void a(DataInputStream datainputstream) throws IOException {
        // uberbukkit -- read the packet in a custom way to allow joining with vastly different PVNs

        // both readUTF() and the minecraft method read short first
        int UTFlen = datainputstream.readShort();
        // if pvn is 11 or higher (aka client uses the minecraft method for transferring strings),
        // `available` will be 2x `UTFlen`
        int available = datainputstream.available();

        this.pvn11 = available > UTFlen;

        byte[] buf = new byte[available];
        datainputstream.readFully(buf, 0, available);

        this.a = new String(buf, "UTF-8").replace(Character.toString((char)0), "");
    }

    public void a(DataOutputStream dataoutputstream) throws IOException {
        // uberbukkit
        if (this.pvn11) {
            a(this.a, dataoutputstream);
        } else {
            dataoutputstream.writeUTF(this.a);
        }
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 4 + this.a.length() + 4;
    }
}
