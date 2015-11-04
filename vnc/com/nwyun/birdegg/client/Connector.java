package com.nwyun.birdegg.client;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class Connector {
	private RfbServer _server;

	public Connector(RfbServer server) {
		_server = server;
	}

	public void connect() {
		System.out.println(_server);
	}
}
