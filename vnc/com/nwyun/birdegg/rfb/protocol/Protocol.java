package com.nwyun.birdegg.rfb.protocol;

import java.util.logging.Logger;

import com.nwyun.birdegg.rfb.IPasswordNeed;
import com.nwyun.birdegg.rfb.protocol.status.ProtocolStatus;
import com.nwyun.birdegg.rfb.protocol.status.VersionStatus;
import com.nwyun.birdegg.server.Server;
import com.nwyun.birdegg.transport.Reader;
import com.nwyun.birdegg.transport.Writer;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class Protocol implements ProtocolContext {
	private final Logger _logger;
	private Reader _reader;
	private Writer _writer;
	private ProtocolStatus _status;
	private final ProtocolSettings _settings;
	private final IPasswordNeed _passwordNeed;
	private Server _server;

	public Protocol(Reader reader, Writer writer, IPasswordNeed passwordNeed,
			ProtocolSettings settings) {
		_logger = Logger.getLogger(getClass().getName());
		_reader = reader;
		_writer = writer;
		_settings = settings;
		_passwordNeed = passwordNeed;
		_status = new VersionStatus(this);
	}

	public void handshake(Server server) {
		_server = server;
		_status.next();
	}

	@Override
	public Writer getWriter() {
		return _writer;
	}

	@Override
	public Reader getReader() {
		return _reader;
	}

	@Override
	public ProtocolSettings getSettings() {
		return _settings;
	}

	@Override
	public IPasswordNeed getPasswordNeed() {
		return _passwordNeed;
	}

	@Override
	public Server getServer() {
		return _server;
	}
}
