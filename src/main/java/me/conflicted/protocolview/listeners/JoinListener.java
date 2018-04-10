package me.conflicted.protocolview.listeners;

import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.conflicted.protocolview.ProtocolViewPlugin;
import me.conflicted.protocolview.version.Version;
import net.minecraft.server.v1_7_R4.EntityPlayer;

/**
 * 
 * @author Conflicted
 *
 */
public class JoinListener implements Listener {

	private final ProtocolViewPlugin plugin;

	public JoinListener(ProtocolViewPlugin plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		EntityPlayer entityPlayer = ((CraftPlayer) player).getHandle();

		plugin.getVersionManager().getVersions().put(player.getUniqueId(),
				Version.getByVersion(entityPlayer.playerConnection.networkManager.getVersion()));
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		plugin.getVersionManager().getVersions().remove(player.getUniqueId());
	}

}
