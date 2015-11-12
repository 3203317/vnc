package com.nwyun.birdegg.server;

import java.util.logging.Logger;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class NwServer extends Server {
	private final Logger _logger;

	public NwServer(String ip, int port, String password) {
		super(ip, port, password);
		_logger = Logger.getLogger(getClass().getName());
	}

	public NwServer(String ip, int port) {
		this(ip, port, null);
	}

}
