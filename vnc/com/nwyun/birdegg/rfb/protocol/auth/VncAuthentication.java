package com.nwyun.birdegg.rfb.protocol.auth;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class VncAuthentication extends AuthHandler {

	@Override
	public SecurityType getType() {
		return SecurityType.VNC_AUTHENTICATION;
	}
}
