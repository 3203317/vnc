package com.nwyun.birdegg.rfb.protocol.status;

import com.nwyun.birdegg.rfb.protocol.ProtocolContext;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class VersionStatus extends ProtocolStatus {

	public VersionStatus(ProtocolContext ctx) {
		super(ctx);
	}

	@Override
	public void next() {
		changeStateTo(new SecurityTypeStatus(ctx));
	}
}
