package com.nwyun.birdegg.client;

import com.nwyun.birdegg.server.Server;
import com.nwyun.birdegg.util.DoWorkHandler;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class Connector {
	private Server _server;

	public Connector(Server server) {
		_server = server;
	}

	public void connect(DoWorkHandler handler) {
		// TODO
		handler.success();
	}
}
