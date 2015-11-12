package com.nwyun.birdegg.client;

import com.nwyun.birdegg.server.Server;
import com.nwyun.birdegg.util.WorkHandler;

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

	public void connect(WorkHandler handler) {
		// TODO
		handler.success();
	}
}
