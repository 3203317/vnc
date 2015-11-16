package com.nwyun.birdegg.rfb.protocol.auth;

import com.nwyun.birdegg.rfb.CapabilityContainer;
import com.nwyun.birdegg.rfb.IPasswordNeed;
import com.nwyun.birdegg.transport.Reader;
import com.nwyun.birdegg.transport.Writer;

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

	public abstract void authenticate(Reader reader, Writer writer,
			CapabilityContainer authCaps, IPasswordNeed passwordNeed);
}
