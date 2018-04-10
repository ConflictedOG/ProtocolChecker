package me.conflicted.protocolview.version;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import lombok.Getter;

/**
 * 
 * @author Conflicted
 *
 */
public class VersionManager {

	@Getter
	private final Map<UUID, Version> versions;
	
	public VersionManager() {
		this.versions = new HashMap<>();
	}
	
	public int getCount(Version countVersion) {
		return (int) versions.entrySet().stream().filter(entry -> entry.getValue() == countVersion).count();
	}
	
	public Version getById(UUID uniqueId) {
		Entry<UUID, Version> version = versions.entrySet().stream().filter(entry -> entry.getKey().equals(uniqueId)).findAny().orElse(null);
		
		return (version == null ? null : version.getValue());
	}
	
}
