package me.conflicted.protocolview.commands;

import java.util.stream.Stream;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.conflicted.protocolview.ProtocolViewPlugin;
import me.conflicted.protocolview.version.Version;
import net.md_5.bungee.api.ChatColor;

/**
 * 
 * @author Notifyz, Conflicted
 *
 */
public class ProtocolCommand implements CommandExecutor {

	private final ProtocolViewPlugin plugin;

	public ProtocolCommand(ProtocolViewPlugin plugin) {
		this.plugin = plugin;
		plugin.getCommand("protocol").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!sender.hasPermission("protocol.check") || !sender.hasPermission("protocol.list")) {
			sender.sendMessage(ChatColor.RED + "You don't have enough permission for this.");
			return true;
		}
		if (args.length == 0 || args.length > 2) {
			sender.sendMessage(ChatColor.RED + "Usage: /" + label + " <check|list> [player]");
			return true;
		}

		if (args[0].equalsIgnoreCase("check")) {
			if (args.length == 2) {
				Player player = Bukkit.getPlayer(args[1]);

				if (player == null) {
					sender.sendMessage(ChatColor.RED + "Player not found.");
					return true;
				}

				if (sender.hasPermission("protocol.check")) {
					Version runningVersion = plugin.getVersionManager().getById(player.getUniqueId());

					sender.sendMessage(ChatColor.GOLD + "Player '" + ChatColor.WHITE + player.getName() + ChatColor.GOLD
							+ "' is using protocol (" + ChatColor.WHITE + runningVersion.toString() + ChatColor.GOLD
							+ ')');
				} else {
					sender.sendMessage(ChatColor.RED + "You don't have enough permission for this.");
					return true;
				}
			} else {
				sender.sendMessage(ChatColor.RED + "Usage: /" + label + " check <player>");
				return true;
			}
		} else if (args[0].equalsIgnoreCase("list")) {
			if (args.length == 1) {
				if (sender.hasPermission("protocol.list")) {
					sender.sendMessage(Stream.of(Version.values())
							.map(version -> ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + version.toString()
									+ ChatColor.DARK_GRAY + "] (" + ChatColor.GRAY
									+ plugin.getVersionManager().getCount(version) + ChatColor.DARK_GRAY + ") \n")
							.findAny().orElse(null));
				} else {
					sender.sendMessage(ChatColor.RED + "You don't have enough permission for this.");
					return true;
				}
			} else {
				sender.sendMessage(ChatColor.RED + "Usage: /" + label + " list");
				return true;
			}
		} else {
			sender.sendMessage(ChatColor.RED + "Unknown sub-command!");
			return true;
		}
		return false;
	}

}