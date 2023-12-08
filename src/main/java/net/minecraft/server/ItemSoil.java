package net.minecraft.server;

public class ItemSoil extends ItemBlock {

    public ItemSoil(int i) {
        super(i);
        this.d(0);
        this.a(true);
    }

    public int filterData(int i) {
        return i;
    }
}
