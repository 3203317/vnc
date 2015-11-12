package com.nwyun.birdegg.rfb.protocol.status;

import com.nwyun.birdegg.rfb.protocol.ProtocolContext;
import com.nwyun.birdegg.transport.Reader;
import com.nwyun.birdegg.transport.Writer;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public abstract class ProtocolStatus {

	protected ProtocolContext ctx;
	protected Reader reader;
	protected Writer writer;

	public ProtocolStatus(ProtocolContext ctx) {
		this.ctx = ctx;
		this.reader = ctx.getReader();
		this.writer = ctx.getWriter();
	}

	protected void changeStateTo(ProtocolStatus status) {
		status.next();
	}

	public abstract void next();
}
