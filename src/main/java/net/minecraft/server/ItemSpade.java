package net.minecraft.server;

import pl.moresteck.uberbukkit.Uberbukkit;

public class ItemSpade extends ItemTool {

    private static Block[] bk;

    public ItemSpade(int i, EnumToolMaterial enumtoolmaterial) {
        super(i, 1, enumtoolmaterial, bk);
    }

    public boolean a(Block block) {
        return block == Block.SNOW ? true : block == Block.SNOW_BLOCK;
    }
    
    static {
        if (Uberbukkit.getPVN() < 12) {
            bk = new Block[] { Block.GRASS, Block.DIRT, Block.SAND, Block.GRAVEL, Block.SNOW, Block.SNOW_BLOCK, Block.CLAY};
        } else {
            bk = new Block[] { Block.GRASS, Block.DIRT, Block.SAND, Block.GRAVEL, Block.SNOW, Block.SNOW_BLOCK, Block.CLAY, Block.SOIL};
        }
    }
}
