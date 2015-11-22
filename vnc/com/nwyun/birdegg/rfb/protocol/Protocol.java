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
	private final Logger logger;
	private Reader reader;
	private Writer writer;
	private ProtocolStatus status;
	private final ProtocolSettings settings;
	private final IPasswordNeed passwordNeed;
	// GET SET
	private int frameBufferHeight;
	private int frameBufferWidth;
	private String remoteDesktopName;
	private PixelFormat pixelFormat;
	private String protocolVersion;

	public Protocol(Reader reader, Writer writer, IPasswordNeed passwordNeed,
			ProtocolSettings settings) {
		logger = Logger.getLogger(getClass().getName());
		this.reader = reader;
		this.writer = writer;
		this.settings = settings;
		this.passwordNeed = passwordNeed;
		status = new ProtocolVersionStatus(this);
	}

	public void handshake() {
		status.execute();
	}

	@Override
	public Writer getWriter() {
		return writer;
	}

	@Override
	public Reader getReader() {
		return reader;
	}

	@Override
	public ProtocolSettings getSettings() {
		return settings;
	}

	@Override
	public IPasswordNeed getPasswordNeed() {
		return passwordNeed;
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

	@Override
	public void setProtocolVersion(String protocolVersion) {
		this.protocolVersion = protocolVersion;
	}

	@Override
	public String getProtocolVersion() {
		return protocolVersion;
	}

}
