package me.devcody.uberbukkit.math;

public class Vec3i extends Vec3<Integer> {
    public Vec3i(int x, int y, int z) {
        super(x, y, z);
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
