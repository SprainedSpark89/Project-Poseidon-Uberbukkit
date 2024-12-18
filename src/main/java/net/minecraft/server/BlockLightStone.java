package net.minecraft.server;

import java.util.Random;

import uk.betacraft.uberbukkit.UberbukkitConfig;

public class BlockLightStone extends Block {

    public BlockLightStone(int i, int j, Material material) {
        super(i, j, material);
    }

    public int a(Random random) {
        // uberbukkit
        if (!UberbukkitConfig.getInstance().getBoolean("mechanics.glowstone_pre1_6_6", false))
            return 2 + random.nextInt(3);

        return super.a(random);
    }

    public int a(int i, Random random) {
        return Item.GLOWSTONE_DUST.id;
    }
}
