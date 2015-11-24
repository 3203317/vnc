package com.nwyun.birdegg.viewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.logging.Logger;

import com.nwyun.birdegg.client.Connector;
import com.nwyun.birdegg.rfb.IPasswordNeed;
import com.nwyun.birdegg.rfb.protocol.ProtocolContext;
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
				new DoWorkHandler<ProtocolContext>() {
					@Override
					public void success(ProtocolContext ctx) {
						createWindow(ctx);
					}

					@Override
					public void failure(Throwable e) {
						e.printStackTrace();
						logger.warning(e.getMessage());
					}
				});
	}

	private void createWindow(ProtocolContext ctx) {
		JWindow window = new JWindow();

		Vitruvian v = new Vitruvian(ctx, 300, 300, 1);
		v.setBackground(Color.YELLOW);

		window.getContentPane().setBackground(Color.CYAN);
		window.getContentPane().add(v, BorderLayout.CENTER);

		window.setSize(new Dimension(400, 400));
		window.pack();
		window.setVisible(true);
		window.validate();
		window.setTitle(ctx.getRemoteDesktopName());
	}

	private class PasswordChooser implements IPasswordNeed {
		@Override
		public String getPassword() {
			return server.getPassword();
		}
	}
}
