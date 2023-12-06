package net.minecraft.server;

public class ItemLongGrass extends ItemBlock {

    public ItemLongGrass(int i) {
        super(i);
        this.d(0);
        this.a(true);
    }

    public int filterData(int i) {
        return i;
    }
}
