package uk.betacraft.uberbukkit.command;

import com.projectposeidon.api.PoseidonUUID;
import com.projectposeidon.api.UUIDType;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.UUID;

public class UuidLookupCommand extends Command {
    public UuidLookupCommand() {
        super("uuidlookup");
        this.usageMessage = "/uuidlookup <username>";
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (args.length != 1) {
            sender.sendMessage(ChatColor.GRAY + "Usage: " + ChatColor.WHITE + "/uuidlookup " + ChatColor.AQUA + "<username>");
            return true;
        }

        UUID uuid = PoseidonUUID.getPlayerUUIDFromCache(args[0], true);
        if (uuid == null) {
            uuid = PoseidonUUID.getPlayerUUIDFromCache(args[0], false);
        }

        if (uuid == null) {
            sender.sendMessage(ChatColor.GRAY + "Unable to locate the UUID of the player called " + ChatColor.AQUA + args[0] + ChatColor.GRAY + "! Please remember usernames are cap sensitive");
            return true;
        }

        UUIDType uuidType = PoseidonUUID.getPlayerUUIDCacheStatus(args[0]);
        sender.sendMessage(ChatColor.GRAY + "uuidlookup > " + ChatColor.AQUA + args[0] + ChatColor.GRAY + " - " + ChatColor.GOLD + "UUID Type: " + ChatColor.YELLOW + uuidType.toString());
        sender.sendMessage(ChatColor.GRAY + "uuidlookup > " + ChatColor.YELLOW + "UUID: " + ChatColor.WHITE + uuid);

        return true;
    }
}
