package com.nwyun.birdegg.viewer;

import java.util.logging.Logger;

import com.nwyun.birdegg.client.Connector;
import com.nwyun.birdegg.rfb.IPasswordNeed;
import com.nwyun.birdegg.rfb.protocol.Protocol;
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

	public void run() {
		server = new NwServer("192.168.6.128", 5901, "123222");
		connector = new Connector(server);
		connector.connect(new DoWorkHandler<Void>() {
			@Override
			public void success(Void v) {
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
		connector.handshake(new PasswordChooser(),
				new DoWorkHandler<Protocol>() {
					@Override
					public void success(Protocol protocol) {
						createWindow(protocol);
					}

					@Override
					public void failure(Throwable e) {
						e.printStackTrace();
						logger.warning(e.getMessage());
					}
				});
	}

	private void createWindow(Protocol protocol) {
		Window window = new JWindow();
		window.setHeight(protocol.getFrameBufferHeight());
		window.setWidth(protocol.getFrameBufferWidth());
		window.open(protocol.getRemoteDesktopName());
	}

	private class PasswordChooser implements IPasswordNeed {
		@Override
		public String getPassword() {
			return server.getPassword();
		}
	}
}
