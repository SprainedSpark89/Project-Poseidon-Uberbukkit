package me.devcody.uberbukkit.util.math;

public class Vec3i {
    private int x, y, z;

    public Vec3i(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }
    public void setZ(int z) {
        this.z = z;
    }

    public Vec3i add(Vec3i vec) {
        return new Vec3i(x + vec.x, y + vec.y, z + vec.z);
    }
    public Vec3i subtract(Vec3i vec) {
        return new Vec3i(x - vec.x, y - vec.y, z - vec.z);
    }

    public Vec3i abs() {
        return new Vec3i(Math.abs(x), Math.abs(y), Math.abs(z));
    }
}
