package me.conflicted.protocolview.version;

import java.util.stream.Stream;

import lombok.Getter;

/**
 * 
 * @author Conflicted
 *
 */
public enum Version {

	V1_7_10(5), V1_7_2(4), V_1_8(47), OTHER(-1);
	
	@Getter
	private final int protocolVersion;
	
	Version(int protocolVersion) {
		this.protocolVersion = protocolVersion;
	}
	
	public static Version getByVersion(int version) {
		return Stream.of(values()).filter(value -> value.getProtocolVersion() == version).findAny().orElse(null);
	}
	
	@Override
	public String toString() {
		return name().replace("V", "").replace("_", ".");
	}
	
}
