package com.nwyun.birdegg.rfb.protocol.auth;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public abstract class AuthHandler {
	public abstract SecurityType getType();

	public int getId() {
		return getType().getId();
	}

	public String getName() {
		return getType().name();
	}
}
