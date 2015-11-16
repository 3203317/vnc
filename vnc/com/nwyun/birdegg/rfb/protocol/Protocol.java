package com.nwyun.birdegg.rfb.protocol;

import java.util.logging.Logger;

import com.nwyun.birdegg.rfb.IPasswordNeed;
import com.nwyun.birdegg.rfb.encoding.PixelFormat;
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
	private final ProtocolSettings _settings;
	private final IPasswordNeed _passwordNeed;
	private int _fbWidth;
	private int _fbHeight;
	private String _remoteDesktopName;
	private PixelFormat _pixelFormat;

	public Protocol(Reader reader, Writer writer, IPasswordNeed passwordNeed,
			ProtocolSettings settings) {
		_logger = Logger.getLogger(getClass().getName());
		_reader = reader;
		_writer = writer;
		_settings = settings;
		_passwordNeed = passwordNeed;
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

	@Override
	public ProtocolSettings getSettings() {
		return _settings;
	}

	@Override
	public IPasswordNeed getPasswordNeed() {
		return _passwordNeed;
	}

	@Override
	public int getFbWidth() {
		return _fbWidth;
	}

	@Override
	public void setFbWidth(int frameBufferWidth) {
		_fbWidth = frameBufferWidth;
	}

	@Override
	public int getFbHeight() {
		return _fbHeight;
	}

	@Override
	public void setFbHeight(int frameBufferHeight) {
		_fbHeight = frameBufferHeight;
	}

	@Override
	public void setRemoteDesktopName(String name) {
		_remoteDesktopName = name;

	}

	@Override
	public String getRemoteDesktopName() {
		return _remoteDesktopName;
	}

	@Override
	public PixelFormat getPixelFormat() {
		return _pixelFormat;
	}

	@Override
	public void setPixelFormat(PixelFormat pixelFormat) {
		_pixelFormat = pixelFormat;
	}
}
