package net.minecraft.server;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;

// CraftBukkit start

import com.legacyminecraft.poseidon.packets.ArtificialPacket53BlockChange;
// CraftBukkit end

import uk.betacraft.uberbukkit.packet.Packet62Sound;
import uk.betacraft.uberbukkit.packet.Packet63Digging;

public class ItemInWorldManager {

    private WorldServer world;
    public EntityHuman player;
    private int c = 0;
    private long lastDigTick;
    public int e;
    public int f;
    public int g;
    private long currentTick;
    private boolean i;
    private int j;
    private int k;
    private int l;
    private long m;

    public double damageDealt;
    public float delaySound; // uberbukkit

    public ItemInWorldManager(WorldServer worldserver) {
        this.world = worldserver;
        this.delaySound = 0.0F;
    }

    // ======= UBERBUKKIT PRE-b1.3 AREA =======

    public void oldClick(int i, int j, int k, int l) { // UberBukkit add block face
        int i1 = this.world.getTypeId(i, j, k);

        // CraftBukkit start
        PlayerInteractEvent event = CraftEventFactory.callPlayerInteractEvent(this.player, Action.LEFT_CLICK_BLOCK, i, j, k, l, this.player.inventory.getItemInHand());

        if (event.useInteractedBlock() == Event.Result.DENY) {
            // If we denied a door from opening, we need to send a correcting update to the client, as it already opened the door.
            if (i1 == Block.WOODEN_DOOR.id) {
                // For some reason *BOTH* the bottom/top part have to be marked updated.
                boolean bottom = (this.world.getData(i, j, k) & 8) == 0;
                ((EntityPlayer) this.player).netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, this.world));
                ((EntityPlayer) this.player).netServerHandler.sendPacket(new Packet53BlockChange(i, j + (bottom ? 1 : -1), k, this.world));
            } else if (i1 == Block.TRAP_DOOR.id) {
                ((EntityPlayer) this.player).netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, this.world));
            }
        } else {
            if (i1 > 0 && this.damageDealt == 0.0F) {
                Block.byId[i1].b(this.world, i, j, k, this.player);
            }
            // Allow fire punching to be blocked
            this.world.douseFire(player, i, j, k, l);
        }
        // CraftBukkit end

        if (i1 > 0 && Block.byId[i1].getDamage(this.player) >= 1.0F) {
            this.c(i, j, k);
        }
    }

    public void oldHaltBreak() {
        this.damageDealt = 0.0F;
        this.c = 0;
    }

    public void oldDig(int i, int j, int k, int l) {
        if (this.c > 0) {
            --this.c;
        } else {
            if (i == this.e && j == this.f && k == this.g) {
                int i1 = this.world.getTypeId(i, j, k);

                if (i1 == 0) {
                    return;
                }

                Block block = Block.byId[i1];

                this.damageDealt += block.getDamage(this.player);

                // uberbukkit - play breaking sound for others
                if (delaySound % 4.0F == 0.0F && block != null) {
                    this.world.makeSound((double) i + 0.5D, (double) j + 0.5D, (double) k + 0.5D, block.stepSound.getName(), (block.stepSound.getVolume1() + 1.0F) / 8.0F, block.stepSound.getVolume2() * 0.5F);
                    ((CraftServer) Bukkit.getServer()).getHandle().sendPacketNearby(player, i, j, k, 64D, player.dimension, new Packet63Digging(i, j, k, l, (float) this.damageDealt));
                }

                delaySound++;

                if (this.damageDealt >= 1.0F) {
                    this.c(i, j, k);
                    this.damageDealt = 0.0F;
                    this.c = 5;
                    delaySound = 0.0F;
                }
            } else {
                this.damageDealt = 0.0F;
                this.e = i;
                this.f = j;
                this.g = k;
                delaySound = 0.0F;
            }
        }
    }

    // ======= END =======

    public void a() {
        this.currentTick = System.currentTimeMillis(); // CraftBukkit
        if (this.i) {
            int i = (int) ((this.currentTick / 50) - (this.m / 50));
            int j = this.world.getTypeId(this.j, this.k, this.l);

            if (j != 0) {
                Block block = Block.byId[j];
                float f = block.getDamage(this.player) * (float) (i + 1);

                if (f >= 1.0F) {
                    this.i = false;
                    this.c(this.j, this.k, this.l);
                }
            } else {
                this.i = false;
            }
        }
    }

    // uberbukkit - make toolDamage from dig(...) accessible for getExpectedDigEnd()
    public float toolDamage = 1.0F;

    public void dig(int i, int j, int k, int l) {
        // this.world.douseFire((EntityHuman) null, i, j, k, l); // CraftBukkit - moved down
        this.lastDigTick = System.currentTimeMillis(); // CraftBukkit
        int i1 = this.world.getTypeId(i, j, k);

        // CraftBukkit start
        // Swings at air do *NOT* exist.
        if (i1 <= 0) {
            return;
        }

        PlayerInteractEvent event = CraftEventFactory.callPlayerInteractEvent(this.player, Action.LEFT_CLICK_BLOCK, i, j, k, l, this.player.inventory.getItemInHand());

        if (event.useInteractedBlock() == Event.Result.DENY) {
            // If we denied a door from opening, we need to send a correcting update to the client, as it already opened the door.
            if (i1 == Block.WOODEN_DOOR.id) {
                // For some reason *BOTH* the bottom/top part have to be marked updated.
                boolean bottom = (this.world.getData(i, j, k) & 8) == 0;
                ((EntityPlayer) this.player).netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, this.world));
                ((EntityPlayer) this.player).netServerHandler.sendPacket(new Packet53BlockChange(i, j + (bottom ? 1 : -1), k, this.world));
            } else if (i1 == Block.TRAP_DOOR.id) {
                ((EntityPlayer) this.player).netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, this.world));
            }
        } else {
            Block.byId[i1].b(this.world, i, j, k, this.player);
            // Allow fire punching to be blocked
            this.world.douseFire((EntityHuman) null, i, j, k, l);
        }

        // Handle hitting a block
        toolDamage = Block.byId[i1].getDamage(this.player);
        if (event.useItemInHand() == Event.Result.DENY) {
            // If we 'insta destroyed' then the client needs to be informed.
            if (toolDamage > 1.0f) {
                ((EntityPlayer) this.player).netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, this.world));
            }
            return;
        }
        BlockDamageEvent blockEvent = CraftEventFactory.callBlockDamageEvent(this.player, i, j, k, this.player.inventory.getItemInHand(), toolDamage >= 1.0f);

        if (blockEvent.isCancelled()) {
            return;
        }

        if (blockEvent.getInstaBreak()) {
            toolDamage = 2.0f;
        }

        Block block = Block.byId[i1];

        // uberbukkit - send digging sound
        if (block != null) {
            float vol1 = (block.stepSound.getVolume1() + 1.0F) / 8.0F;

            ((CraftServer) Bukkit.getServer()).getHandle().sendPacketNearbyToScale(this.player, (double) i + 0.5D, (double) j + 0.5D, (double) k + 0.5D, vol1, ((WorldServer) this.player.world).dimension, new Packet62Sound(block.stepSound.getName(), (double) i + 0.5D, (double) j + 0.5D, (double) k + 0.5D, vol1, block.stepSound.getVolume2() * 0.5F));
        }

        if (toolDamage >= 1.0F) {
            // CraftBukkit end
            this.c(i, j, k);
        } else {
            this.e = i;
            this.f = j;
            this.g = k;
        }
    }

    public void a(int i, int j, int k) {
        if (i == this.e && j == this.f && k == this.g) {
            this.currentTick = System.currentTimeMillis(); // CraftBukkit
            int l = (int) ((this.currentTick / 50) - (this.lastDigTick / 50));
            int i1 = this.world.getTypeId(i, j, k);

            if (i1 != 0) {
                Block block = Block.byId[i1];
                float f = block.getDamage(this.player) * (float) (l + 1);

                if (f >= 0.7F) {
                    this.c(i, j, k);
                } else if (!this.i) {
                    this.i = true;
                    this.j = i;
                    this.k = j;
                    this.l = k;
                    this.m = this.lastDigTick;
                }
            }
            // CraftBukkit start - force blockreset to client
        } else {
            ((EntityPlayer) this.player).netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, this.world));
            // CraftBukkit end
        }
    }

    public long getExpectedDigEnd() {
        return (long) (1F / toolDamage / 0.0214285703109844D) + this.lastDigTick;
    }

    public int getCurrentMagic() {
        return (int) ((System.currentTimeMillis() / 50) - (this.lastDigTick / 50));
    }

    public boolean b(int i, int j, int k) {
        Block block = Block.byId[this.world.getTypeId(i, j, k)];
        int l = this.world.getData(i, j, k);
        boolean flag = this.world.setTypeId(i, j, k, 0);

        if (block != null && flag) {
            block.postBreak(this.world, i, j, k, l);
        }

        return flag;
    }

    public boolean c(int i, int j, int k) {
        // CraftBukkit start
        if (this.player instanceof EntityPlayer) {
            org.bukkit.block.Block block = this.world.getWorld().getBlockAt(i, j, k);

            //Project Poseidon Start - Craft Bukkit backport
            // Tell client the block is gone immediately then process events
            if (world.getTileEntity(i, j, k) == null) {
                ((EntityPlayer) this.player).netServerHandler.sendPacket(new ArtificialPacket53BlockChange(i, j, k, 0, 0));
            }
            //Project Poseidon End
            BlockBreakEvent event = new BlockBreakEvent(block, (org.bukkit.entity.Player) this.player.getBukkitEntity());
            this.world.getServer().getPluginManager().callEvent(event);

            if (event.isCancelled()) {
                return false;
            }
        }
        // CraftBukkit end

        int l = this.world.getTypeId(i, j, k);
        int i1 = this.world.getData(i, j, k);

        this.world.a(this.player, 2001, i, j, k, l + this.world.getData(i, j, k) * 256);
        boolean flag = this.b(i, j, k);
        ItemStack itemstack = this.player.G();

        if (itemstack != null) {
            itemstack.a(l, i, j, k, this.player);
            // Uberbukkit - ref1
        }

        if (flag && this.player.b(Block.byId[l])) {
            Block.byId[l].a(this.world, this.player, i, j, k, i1);
            ((EntityPlayer) this.player).netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, this.world));
        }

        // Uberbukkit - moved from ref1
        // Fix tools not dropping the block on their last use
        // TODO maybe make it optional?
        if (itemstack != null) {
            if (itemstack.count == 0) {
                itemstack.a(this.player);
                this.player.H();
            }
        }

        return flag;
    }

    public boolean useItem(EntityHuman entityhuman, World world, ItemStack itemstack) {
        int i = itemstack.count;
        ItemStack itemstack1 = itemstack.a(world, entityhuman);

        if (itemstack1 == itemstack && (itemstack1 == null || itemstack1.count == i)) {
            return false;
        } else {
            entityhuman.inventory.items[entityhuman.inventory.itemInHandIndex] = itemstack1;
            if (itemstack1.count == 0) {
                entityhuman.inventory.items[entityhuman.inventory.itemInHandIndex] = null;
            }

            return true;
        }
    }

    public boolean interact(EntityHuman entityhuman, World world, ItemStack itemstack, int i, int j, int k, int l) {
        int i1 = world.getTypeId(i, j, k);

        // CraftBukkit start - Interact
        boolean result = false;
        if (i1 > 0) {
            PlayerInteractEvent event = CraftEventFactory.callPlayerInteractEvent(entityhuman, Action.RIGHT_CLICK_BLOCK, i, j, k, l, itemstack);
            if (event.useInteractedBlock() == Event.Result.DENY) {
                // If we denied a door from opening, we need to send a correcting update to the client, as it already opened the door.
                if (i1 == Block.WOODEN_DOOR.id) {
                    boolean bottom = (world.getData(i, j, k) & 8) == 0;
                    ((EntityPlayer) entityhuman).netServerHandler.sendPacket(new Packet53BlockChange(i, j + (bottom ? 1 : -1), k, world));
                }
                result = (event.useItemInHand() != Event.Result.ALLOW);
            } else {
                result = Block.byId[i1].interact(world, i, j, k, entityhuman);
            }

            if (itemstack != null && !result) {
                result = itemstack.placeItem(entityhuman, world, i, j, k, l);
            }

            // If we have 'true' and no explicit deny *or* an explicit allow -- run the item part of the hook
            if (itemstack != null && ((!result && event.useItemInHand() != Event.Result.DENY) || event.useItemInHand() == Event.Result.ALLOW)) {
                this.useItem(entityhuman, world, itemstack);
            }
        }
        return result;
        // CraftBukkit end
    }
}
