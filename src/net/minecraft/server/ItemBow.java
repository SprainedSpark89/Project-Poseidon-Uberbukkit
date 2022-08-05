package net.minecraft.server;

import com.legacyminecraft.poseidon.PoseidonConfig;

public class ItemBow extends Item {

    public ItemBow(int i) {
        super(i);
        this.maxStackSize = 1;
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        if (entityhuman.inventory.b(Item.ARROW.id)) {

            // uberbukkit
            if (PoseidonConfig.getInstance().getBoolean("world.settings.skeleton-shooting-sound-fix.enabled", true)) {
                world.e(1002, MathHelper.floor(entityhuman.locX), MathHelper.floor(entityhuman.locY - (double)entityhuman.height), MathHelper.floor(entityhuman.locZ), 0); // Poseidon - fix skeleton bow sounds (Strultz)
            } else {
                world.makeSound(entityhuman, "random.bow", 1.0F, 1.0F / (b.nextFloat() * 0.4F + 0.8F));
            }

            if (!world.isStatic) {
                EntityArrow entityarrow = new EntityArrow(world, entityhuman);
                world.addEntity(entityarrow);
            }
        }

        return itemstack;
    }
}
