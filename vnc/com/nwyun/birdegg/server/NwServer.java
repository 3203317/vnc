package com.nwyun.birdegg.server;

import com.nwyun.birdegg.util.LogWriter;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class NwServer extends Server {
	final static LogWriter _log = new LogWriter("NwServer");

	public NwServer(String ip, int port, String password) {
		super(ip, port, password);
	}

	public NwServer(String ip, int port) {
		this(ip, port, null);
	}

}
