package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

public class Packet24MobSpawn extends Packet {

    public int a;
    public byte b;
    public int c;
    public int d;
    public int e;
    public byte f;
    public byte g;
    private DataWatcher h;
    private List i;

    public Packet24MobSpawn() {
    }

    public Packet24MobSpawn(EntityLiving entityliving) {
        this.a = entityliving.id;
        this.b = (byte) EntityTypes.a(entityliving);
        this.c = MathHelper.floor(entityliving.locX * 32.0D);
        this.d = MathHelper.floor(entityliving.locY * 32.0D);
        this.e = MathHelper.floor(entityliving.locZ * 32.0D);
        this.f = (byte) ((int) (entityliving.yaw * 256.0F / 360.0F));
        this.g = (byte) ((int) (entityliving.pitch * 256.0F / 360.0F));
        this.h = entityliving.aa();
    }

    public void a(DataInputStream datainputstream) throws IOException {
        this.a = datainputstream.readInt();
        this.b = datainputstream.readByte();
        this.c = datainputstream.readInt();
        this.d = datainputstream.readInt();
        this.e = datainputstream.readInt();
        this.f = datainputstream.readByte();
        this.g = datainputstream.readByte();
        // uberbukkit
        if (this.pvn >= 8) {
            this.i = DataWatcher.a(datainputstream);
        } else {
            this.i = null;
        }
    }

    public void a(DataOutputStream dataoutputstream) throws IOException {
        dataoutputstream.writeInt(this.a);

        byte entityType = this.b;
        // uberbukkit - a1.1.2_01 doesn't recognize cows and sheep
        if (this.pvn <= 2 && (this.b == 92 || this.b == 93))
            entityType = 91;

        dataoutputstream.writeByte(entityType);
        dataoutputstream.writeInt(this.c);
        dataoutputstream.writeInt(this.d);
        dataoutputstream.writeInt(this.e);
        dataoutputstream.writeByte(this.f);
        dataoutputstream.writeByte(this.g);
        // uberbukkit
        if (this.pvn >= 8) {
            this.h.a(dataoutputstream);
        }
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        // uberbukkit
        return this.pvn >= 8 ? 20 : 19;
    }
}
