package com.nwyun.birdegg.viewer;

import java.util.logging.Logger;

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
	private final Logger logger;
	private Connector connector;
	private Server server;

	public static void main(String[] args) {
		Test _t = new Test();
		_t.run();
	}

	public Test() {
		logger = Logger.getLogger(getClass().getName());
	}

	@Override
	public void run() {
		server = new NwServer("192.168.6.128", 5901, "123222");
		connector = new Connector(server);
		connector.connect(new DoWorkHandler() {
			@Override
			public void success() {
				connect();
			}

			@Override
			public void failure(Throwable e) {
				e.printStackTrace();
				logger.warning(e.getMessage());
			}
		});
	}

	private void connect() {
		connector.handshake(new PasswordChooser(), new DoWorkHandler() {
			@Override
			public void success() {
				createWindow();
			}

			@Override
			public void failure(Throwable e) {
				e.printStackTrace();
				logger.warning(e.getMessage());
			}
		});
	}

	private void createWindow() {
		JWindow _window = new JWindow();
		_window.setServer(server);
		_window.setHeight(768);
		_window.setWidth(1024);
		_window.open(server.getName());
	}

	private class PasswordChooser implements IPasswordNeed {
		@Override
		public String getPassword() {
			return server.getPassword();
		}
	}
}
