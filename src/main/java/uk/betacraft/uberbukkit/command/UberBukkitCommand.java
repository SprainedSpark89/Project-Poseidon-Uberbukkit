package uk.betacraft.uberbukkit.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import uk.betacraft.uberbukkit.Uberbukkit;

public class UberBukkitCommand extends Command {
    public UberBukkitCommand() {
        super("uberbukkit");
        this.description = "Shows information about the UberBukkit version ran on the server";
        this.usageMessage = "/uberbukkit";
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        sender.sendMessage(ChatColor.GRAY + "This server is running " + ChatColor.RED + "UberBukkit " + ChatColor.GOLD + Uberbukkit.getVersion());
        sender.sendMessage(ChatColor.GRAY + "Based on Project Poseidon " + ChatColor.AQUA + Bukkit.getServer().getPoseidonVersion());

        return true;
    }
}
