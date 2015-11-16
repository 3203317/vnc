package com.nwyun.birdegg.client;

import java.net.Socket;
import java.util.logging.Logger;

import com.nwyun.birdegg.exception.ConnectSocketException;
import com.nwyun.birdegg.rfb.IPasswordNeed;
import com.nwyun.birdegg.rfb.protocol.Protocol;
import com.nwyun.birdegg.rfb.protocol.ProtocolSettings;
import com.nwyun.birdegg.server.Server;
import com.nwyun.birdegg.transport.Reader;
import com.nwyun.birdegg.transport.Writer;
import com.nwyun.birdegg.util.DoWorkHandler;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class Connector {
	private final Logger _logger;
	private Server _server;
	private Socket _socket;
	private Reader _reader;
	private Writer _writer;
	private ProtocolSettings _settings;
	private Protocol _protocol;

	public Connector(Server server) {
		_logger = Logger.getLogger(getClass().getName());
		_server = server;
	}

	public void test(DoWorkHandler handler) {
		_logger.info("connect remote socket " + _server.getIp() + ":"
				+ _server.getPort());
		try {
			_socket = new Socket(_server.getIp(), _server.getPort());
			_socket.setTcpNoDelay(true); // disable Nagle algorithm
			_reader = new Reader(_socket.getInputStream());
			_writer = new Writer(_socket.getOutputStream());
		} catch (Exception e) {
			throw new ConnectSocketException();
		}
		handler.success();
	}

	public void connect(DoWorkHandler handler) {
		_logger.info("rfb server handshake");
		_settings = ProtocolSettings.getDefaultSettings();
		_protocol = new Protocol(_reader, _writer, new IPasswordNeed() {
			@Override
			public String getPassword() {
				return _server.getPassword();
			}
		}, _settings);
		_protocol.handshake(_server);
		handler.success();
	}
}
