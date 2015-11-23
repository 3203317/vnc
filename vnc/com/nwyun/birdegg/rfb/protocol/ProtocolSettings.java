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
	private boolean sharedFlag;
	private static ProtocolSettings settings;

	private ProtocolSettings() {
		sharedFlag = true;
		authCapabilities = new CapabilityContainer();
	}

	public static ProtocolSettings getDefaultSettings() {
		if (null == settings)
			settings = new ProtocolSettings();
		return settings;
	}

	public byte getSharedFlag() {
		return (byte) (sharedFlag ? 1 : 0);
	}

	public void enableAllEncodingCaps() {
		// TODO
	}
}
