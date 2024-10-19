package net.minecraft.server;

// CraftBukkit start

import org.bukkit.craftbukkit.block.CraftBlockState;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.block.BlockPlaceEvent;
// CraftBukkit end

import com.legacyminecraft.poseidon.PoseidonConfig;

public class ItemSeeds extends Item {

    private int id;

    public ItemSeeds(int i, int j) {
        super(i);
        this.id = j;
    }

    public boolean a(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l) {
        if (l != 1) {
            return false;
        } else {
            int i1 = world.getTypeId(i, j, k);

            // uberbukkit start
            boolean isEmptyFlag = world.isEmpty(i, j + 1, k);

            if (PoseidonConfig.getInstance().getBoolean("version.mechanics.seeds_replace_blocks", false)) {
                isEmptyFlag = true;
            }

            if (i1 == Block.SOIL.id && isEmptyFlag) {
                // uberbukkit end
                CraftBlockState blockState = CraftBlockState.getBlockState(world, i, j + 1, k); // CraftBukkit

                world.setTypeId(i, j + 1, k, this.id);

                // CraftBukkit start - seeds
                BlockPlaceEvent event = CraftEventFactory.callBlockPlaceEvent(world, entityhuman, blockState, i, j, k, this.id);

                if (event.isCancelled() || !event.canBuild()) {
                    event.getBlockPlaced().setTypeId(0);
                    return false;
                }
                // CraftBukkit end

                --itemstack.count;
                return true;
            } else {
                return false;
            }
        }
    }
}
