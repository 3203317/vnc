package com.nwyun.birdegg.rfb.protocol.auth;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public enum SecurityType {
	NONE_AUTHENTICATION(1), VNC_AUTHENTICATION(2);
	// int RA2_AUTHENTICATION = 5;
	// int RA2NE_AUTHENTICATION = 6;
	// int ULTRA_AUTHENTICATION = 17;
	// int TLS_AUTHENTICATION = 18;
	// int VENCRYPT_AUTHENTICATION = 19;

	private int id;

	private SecurityType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	@SuppressWarnings("serial")
	public static Map<Integer, AuthHandler> implementedSecurityTypes = new LinkedHashMap<Integer, AuthHandler>() {
		{
			put(VNC_AUTHENTICATION.getId(), new VncAuthentication());
		}
	};
}
