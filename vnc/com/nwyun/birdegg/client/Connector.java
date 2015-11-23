package com.nwyun.birdegg.client;

import java.net.Socket;
import java.util.logging.Logger;

import com.nwyun.birdegg.rfb.IPasswordNeed;
import com.nwyun.birdegg.rfb.protocol.Protocol;
import com.nwyun.birdegg.rfb.protocol.ProtocolSettings;
import com.nwyun.birdegg.server.Server;
import com.nwyun.birdegg.transport.Reader;
import com.nwyun.birdegg.transport.Writer;
import com.nwyun.birdegg.util.DoWorkHandler;
import com.nwyun.birdegg.viewer.Window;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class Connector {
	private final Logger logger;
	private Server server;
	private Socket socket;
	private Reader reader;
	private Writer writer;
	private ProtocolSettings settings;
	private Protocol protocol;

	public Connector(Server server) {
		logger = Logger.getLogger(getClass().getName());
		this.server = server;
	}

	public void connect(DoWorkHandler $handler) {
		logger.info("Connect remote socket " + server.getIp() + ":"
				+ server.getPort());
		try {
			socket = new Socket(server.getIp(), server.getPort());
			socket.setTcpNoDelay(true); // disable Nagle algorithm
			reader = new Reader(socket.getInputStream());
			writer = new Writer(socket.getOutputStream());
		} catch (Exception e) {
			$handler.failure(e);
			return;
		}
		$handler.success();
	}

	public void handshake(IPasswordNeed $passwordNeed, DoWorkHandler $handler) {
		logger.info("RFB Server handshake");
		settings = ProtocolSettings.getDefaultSettings();
		// TODO
		protocol = new Protocol(reader, writer, $passwordNeed, settings);
		try {
			protocol.handshake();
		} catch (Exception e) {
			$handler.failure(e);
			return;
		}
		$handler.success();
	}

	public void createWindow(Window $window, DoWorkHandler $handler) {
		$window.setHeight(protocol.getFrameBufferHeight());
		$window.setWidth(protocol.getFrameBufferWidth());
		$window.open(protocol.getRemoteDesktopName());
		$handler.success();
	}
}
