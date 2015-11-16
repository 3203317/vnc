package com.nwyun.birdegg.rfb.protocol;

import java.io.Serializable;

import com.nwyun.birdegg.rfb.CapabilityContainer;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class ProtocolSettings implements Serializable {
	private static final long serialVersionUID = -4841444862172719558L;

	public transient CapabilityContainer authCapabilities;

	private ProtocolSettings() {
		authCapabilities = new CapabilityContainer();
	}

	public static ProtocolSettings getDefaultSettings() {
		ProtocolSettings settings = new ProtocolSettings();
		return settings;
	}
}
