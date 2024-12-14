package net.minecraft.server;

import java.util.Random;

import uk.betacraft.uberbukkit.UberbukkitConfig;

public class BiomeForest extends BiomeBase {

    public BiomeForest() {
        // uberbukkit
        if (UberbukkitConfig.getInstance().getBoolean("mechanics.spawn_wolves", true))
            this.t.add(new BiomeMeta(EntityWolf.class, 2));
    }

    public WorldGenerator a(Random random) {
        // uberbukkit
        if (UberbukkitConfig.getInstance().getBoolean("worldgen.biomes.generate_birches", true) && random.nextInt(5) == 0) {
            return new WorldGenForest();
        } else if (random.nextInt(3) == 0) {
            return new WorldGenBigTree();
        } else {
            return new WorldGenTrees();
        }
    }
}
