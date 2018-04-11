package me.conflicted.protocolview.version;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.bukkit.entity.Player;

import lombok.Getter;
import me.conflicted.protocolview.ProtocolViewPlugin;
import net.md_5.bungee.api.ChatColor;

/**
 * 
 * @author Conflicted
 *
 */
public class VersionManager {

	@Getter
	private final Map<UUID, Version> versions;
	private final ProtocolViewPlugin plugin;

	public VersionManager(ProtocolViewPlugin plugin) {
		this.plugin = plugin;
		this.versions = new HashMap<>();
	}

	public int getCount(Version countVersion) {
		return (int) versions.entrySet().stream().filter(entry -> entry.getValue() == countVersion).count();
	}

	public Version getById(UUID uniqueId) {
		Entry<UUID, Version> version = versions.entrySet().stream().filter(entry -> entry.getKey().equals(uniqueId))
				.findAny().orElse(null);

		return (version == null ? null : version.getValue());
	}

	public Set<String> getByVersion(Version version) {
		Set<String> players = new HashSet<>();

		for (Entry<UUID, Version> entry : versions.entrySet()) {
			if (entry.getValue() == version) {
				Player player = null;
				if ((player = plugin.getServer().getPlayer(entry.getKey())) != null)
					players.add(ChatColor.GOLD + player.getName());
			}
		}

		return players;
	}

}
