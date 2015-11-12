package com.nwyun.birdegg.rfb.protocol;

import java.util.logging.Logger;

import com.nwyun.birdegg.rfb.protocol.status.ProtocolStatus;
import com.nwyun.birdegg.rfb.protocol.status.VersionStatus;
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

	public Protocol(Reader reader, Writer writer) {
		_logger = Logger.getLogger(getClass().getName());
		_reader = reader;
		_writer = writer;
		_status = new VersionStatus(this);
	}

	public void handshake() {
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
}
