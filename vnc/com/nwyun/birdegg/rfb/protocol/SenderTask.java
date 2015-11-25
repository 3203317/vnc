package com.nwyun.birdegg.rfb.protocol;

import com.nwyun.birdegg.transport.Writer;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class SenderTask implements Runnable {

	private MessageQueue messageQueue;
	private final Writer writer;
	private final ProtocolContext ctx;
	private volatile boolean isRunning = false;

	public SenderTask(MessageQueue messageQueue, Writer writer,
			ProtocolContext ctx) {
		this.messageQueue = messageQueue;
		this.writer = writer;
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
