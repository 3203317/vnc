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
	private boolean _sharedFlag;
	private static ProtocolSettings _settings;

	private ProtocolSettings() {
		_sharedFlag = true;
		authCapabilities = new CapabilityContainer();
	}

	public static ProtocolSettings getDefaultSettings() {
		if (null != _settings)
			return _settings;
		_settings = new ProtocolSettings();
		return _settings;
	}

	public byte getSharedFlag() {
		return (byte) (_sharedFlag ? 1 : 0);
	}
}
