package com.nwyun.birdegg.client;

import rfb.LogWriter;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class Test implements Runnable {
	static LogWriter vlog = new LogWriter("Test");
	private RfbServer _server;

	public static void main(String[] args) {
		Test t = new Test();

		Thread thread = new Thread(t);
		thread.run();
	}

	@Override
	public void run() {
		_server = new NwServer("192.168.6.128", 5901, "123222");
		Connector ctor = new Connector(_server);
		ctor.connect();
	}
}
