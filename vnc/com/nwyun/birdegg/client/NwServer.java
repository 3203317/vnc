package com.nwyun.birdegg.client;

import rfb.LogWriter;

import com.nwyun.birdegg.rfb.Server;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class NwServer extends Server {
	final static LogWriter vlog = new LogWriter("NwServer");

	public NwServer(String ip, int port, String password) {
		super(ip, port, password);
	}
}
