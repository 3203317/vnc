package com.nwyun.birdegg.rfb.protocol;

import java.util.logging.Logger;

import com.nwyun.birdegg.rfb.IPasswordNeed;
import com.nwyun.birdegg.rfb.encoding.PixelFormat;
import com.nwyun.birdegg.rfb.protocol.status.ProtocolStatus;
import com.nwyun.birdegg.rfb.protocol.status.ProtocolVersionStatus;
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
	// GET SET
	private int frameBufferHeight;
	private int frameBufferWidth;
	private String remoteDesktopName;
	private PixelFormat pixelFormat;

	public Protocol(Reader reader, Writer writer, IPasswordNeed passwordNeed,
			ProtocolSettings settings) {
		_logger = Logger.getLogger(getClass().getName());
		_reader = reader;
		_writer = writer;
		_settings = settings;
		_passwordNeed = passwordNeed;
		_status = new ProtocolVersionStatus(this);
	}

	public void handshake() {
		_status.execute();
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
	public void setFrameBufferHeight(int frameBufferHeight) {
		this.frameBufferHeight = frameBufferHeight;
	}

	@Override
	public int getFrameBufferHeight() {
		return frameBufferHeight;
	}

	@Override
	public void setFrameBufferWidth(int frameBufferWidth) {
		this.frameBufferWidth = frameBufferWidth;
	}

	@Override
	public int getFrameBufferWidth() {
		return frameBufferWidth;
	}

	@Override
	public void setRemoteDesktopName(String remoteDesktopName) {
		this.remoteDesktopName = remoteDesktopName;
	}

	@Override
	public String getRemoteDesktopName() {
		return remoteDesktopName;
	}

	@Override
	public void setPixelFormat(PixelFormat pixelFormat) {
		this.pixelFormat = pixelFormat;
	}

	@Override
	public PixelFormat getPixelFormat() {
		return pixelFormat;
	}

}
