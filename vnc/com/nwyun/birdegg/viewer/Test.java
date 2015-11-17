package com.nwyun.birdegg.viewer;

import com.nwyun.birdegg.client.Connector;
import com.nwyun.birdegg.rfb.IPasswordNeed;
import com.nwyun.birdegg.server.NwServer;
import com.nwyun.birdegg.server.Server;
import com.nwyun.birdegg.util.DoWorkHandler;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class Test implements Runnable {
	private Connector _connector;
	private Server _server;

	public static void main(String[] args) {
		Test t = new Test();
		t.run();
	}

	public Test() {
		// TODO
	}

	@Override
	public void run() {
		_server = new NwServer("192.168.6.128", 5901);
		_connector = new Connector(_server);
		_connector.connect(new DoWorkHandler() {
			@Override
			public void success() {
				connect();
			}
		});
	}

	private void connect() {
		_connector.handshake(new PasswordChooser(), new DoWorkHandler() {
			@Override
			public void success() {
				createWindow();
			}
		});
	}

	private void createWindow() {
		JWindow window = new JWindow();
		window.setServer(_server);
		window.setHeight(_server.getHeight());
		window.setWidth(_server.getWidth());
		window.open(_server.getName());
	}

	private class PasswordChooser implements IPasswordNeed {
		@Override
		public String getPassword() {
			return "123222";
		}
	}
}
