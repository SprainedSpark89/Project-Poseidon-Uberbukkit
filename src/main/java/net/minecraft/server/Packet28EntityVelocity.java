package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet28EntityVelocity extends Packet {

    public int a;
    public double b;
    public double c;
    public double d;

    public Packet28EntityVelocity() {
    }

    public Packet28EntityVelocity(Entity entity) {
        this(entity.id, entity.motX, entity.motY, entity.motZ);
    }

    public Packet28EntityVelocity(int i, double d0, double d1, double d2) {
        this.a = i;
        // uberbukkit - values are calculated on sending to achieve compatibility
        // TODO: test impact on performance?
        this.b = d0;
        this.c = d1;
        this.d = d2;
    }

    public void a(DataInputStream datainputstream) throws IOException {
        this.a = datainputstream.readInt();
        this.b = datainputstream.readShort();
        this.c = datainputstream.readShort();
        this.d = datainputstream.readShort();
    }

    public void a(DataOutputStream dataoutputstream) throws IOException {
        dataoutputstream.writeInt(this.a);

        // uberbukkit start
        double d3 = 3.9D;
        if (this.pvn <= 5)
            d3 = 0.9D;

        if (this.b < -d3) {
            this.b = -d3;
        }

        if (this.c < -d3) {
            this.c = -d3;
        }

        if (this.d < -d3) {
            this.d = -d3;
        }

        if (this.b > d3) {
            this.b = d3;
        }

        if (this.c > d3) {
            this.c = d3;
        }

        if (this.d > d3) {
            this.d = d3;
        }

        double multiplier = 8000.0D;
        if (this.pvn <= 5)
            multiplier = 32000.0D;

        dataoutputstream.writeShort((int) (this.b * multiplier));
        dataoutputstream.writeShort((int) (this.c * multiplier));
        dataoutputstream.writeShort((int) (this.d * multiplier));
        // uberbukkit end
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 10;
    }
}
