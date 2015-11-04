package com.nwyun.birdegg.client;

import rfb.LogWriter;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class NwServer extends RfbServer {
	final static LogWriter vlog = new LogWriter("NwServer");

	public NwServer(String ip, int port, String password) {
		super(ip, port, password);
	}
}
