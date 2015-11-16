package com.nwyun.birdegg.rfb.encoding;

import com.nwyun.birdegg.transport.Reader;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class ServerInitMessage {
	private int _frameBufferWidth;
	private int _frameBufferHeight;
	private PixelFormat _pixelFormat;
	private String _name;

	public ServerInitMessage(Reader reader) {
		_frameBufferWidth = reader.readUInt16();
		_frameBufferHeight = reader.readUInt16();
		_pixelFormat = new PixelFormat();
		_pixelFormat.fill(reader);
		_name = reader.readString();
	}

	public int getFrameBufferWidth() {
		return _frameBufferWidth;
	}

	public int getFrameBufferHeight() {
		return _frameBufferHeight;
	}

	public PixelFormat getPixelFormat() {
		return _pixelFormat;
	}

	public String getName() {
		return _name;
	}

	@Override
	public String toString() {
		return "ServerInitMessage: [name: " + _name + ", framebuffer-width: "
				+ String.valueOf(_frameBufferWidth) + ", framebuffer-height: "
				+ String.valueOf(_frameBufferHeight)
				+ ", server-pixel-format: " + _pixelFormat + "]";
	}
}
