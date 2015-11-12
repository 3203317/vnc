package com.nwyun.birdegg.client;

import java.net.Socket;

import com.nwyun.birdegg.rfb.protocol.VersionInterceptor;
import com.nwyun.birdegg.server.Server;
import com.nwyun.birdegg.util.DoWorkHandler;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class Connector {
	private Server _server;
	private Socket _socket;

	public Connector(Server server) {
		_server = server;
	}

	public void connect(DoWorkHandler handler) {
		try {
			_socket = new Socket(_server.getIp(), _server.getPort());
		} catch (Exception e) {
			e.printStackTrace();
			handler.failure();
			return;
		}
		// TODO
		handler.success();
	}

	private void handshake() {
		VersionInterceptor version = new VersionInterceptor();
	}

	public void handshake(DoWorkHandler handler) {
		handshake();
		handler.success();
	}
}
