package com.nwyun.birdegg.client;

import rfb.LogWriter;

import com.nwyun.birdegg.server.Server;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class Test implements Runnable {
	static LogWriter vlog = new LogWriter("Test");
	private Server _server;

	public static void main(String[] args) {
		Test t = new Test();
		// TODO
		Thread thread = new Thread(t);
		thread.run();
	}

	@Override
	public void run() {
		_server = new NwServer("192.168.6.128", 5901, "123222");
		Connector ctor = null;
		try {
			ctor = new Connector(_server);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		ctor.connect();
	}
}
