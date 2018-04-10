package me.conflicted.protocolview;

import java.util.stream.Stream;

import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import me.conflicted.protocolview.commands.ProtocolCommand;
import me.conflicted.protocolview.listeners.JoinListener;
import me.conflicted.protocolview.version.Version;
import me.conflicted.protocolview.version.VersionManager;
import net.minecraft.server.v1_7_R4.EntityPlayer;

/**
 * 
 * @author Conflicted
 *
 */

public class ProtocolViewPlugin extends JavaPlugin {

	@Getter
	private VersionManager versionManager;
	
	private JoinListener joinListener;
	private ProtocolCommand protocolCommand;
	
	@Override
	public void onLoad() {/* NO OP */}

	@Override
	public void onEnable() {
		this.loadManagers();
		this.loadCommands();
		this.loadListeners();
		Stream.of(this.getServer().getOnlinePlayers()).forEach(player -> {
			EntityPlayer entityPlayer = ((CraftPlayer)player).getHandle();
			getVersionManager().getVersions().put(player.getUniqueId(), Version.getByVersion(entityPlayer.playerConnection.networkManager.getVersion()));
		});
	}

	@Override
	public void onDisable() {/* NO OP */}

	private void loadManagers() {
		this.versionManager = new VersionManager();
	}
	
	private void loadCommands() {
		this.protocolCommand = new ProtocolCommand(this);
	}

	private void loadListeners() {
		this.joinListener = new JoinListener(this);
	}

}
