package com.nwyun.birdegg.rfb.protocol;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import com.nwyun.birdegg.rfb.client.ClientToServerMessage;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class MessageQueue {
	private final BlockingQueue<ClientToServerMessage> queue;

	public MessageQueue() {
		queue = new LinkedBlockingQueue<ClientToServerMessage>();
	}

	public void put(ClientToServerMessage message) {
		queue.offer(message);
	}

	public ClientToServerMessage get() throws InterruptedException {
		return queue.poll(1, TimeUnit.SECONDS);
	}

}
