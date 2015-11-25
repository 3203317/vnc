package com.nwyun.birdegg.rfb.protocol;

import com.nwyun.birdegg.transport.Reader;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class ReceiverTask implements Runnable {
	private final Reader reader;
	private final ProtocolContext ctx;
	private volatile boolean isRunning = false;

	public ReceiverTask(Reader reader, ProtocolContext ctx) {
		this.reader = reader;
		this.ctx = ctx;
	}

	@Override
	public void run() {
		isRunning = true;
		while (isRunning) {
			// TODO
		}
	}

	public void stopTask() {
		isRunning = false;
	}
}
