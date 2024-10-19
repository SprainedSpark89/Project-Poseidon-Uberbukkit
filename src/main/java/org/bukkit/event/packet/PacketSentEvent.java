package org.bukkit.event.packet;

import net.minecraft.server.Packet;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class PacketSentEvent extends Event {
    private Player player;
    private Packet packet;
    private boolean cancelled;

    public PacketSentEvent(Player player, Packet packet) {
        super(Type.PACKET_SENT);

        this.player = player;
        this.packet = packet;
    }

    @Nullable
    public Player getPlayer() {
        return player;
    }

    public Packet getPacket() {
        return packet;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
