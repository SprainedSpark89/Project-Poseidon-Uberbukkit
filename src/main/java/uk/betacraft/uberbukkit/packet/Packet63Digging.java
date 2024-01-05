package uk.betacraft.uberbukkit.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.NetHandler;
import net.minecraft.server.Packet;

public class Packet63Digging extends Packet {
    public int x;
    public int y;
    public int z;
    public int face;
    public float progress;

    public Packet63Digging(int x, int y, int z, int face, float progress) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.face = face;
        this.progress = progress;
    }

    public void a(DataInputStream var1) throws IOException {}

    public void a(DataOutputStream var1) throws IOException {
        var1.writeInt(this.x);
        var1.writeInt(this.y);
        var1.writeInt(this.z);
        var1.writeByte(this.face);
        var1.writeFloat(this.progress);
    }

    public void a(NetHandler var1) {
        var1.handle63Digging(this);
    }

    public int a() {
        return 4 * 4 + 1;
    }
}
