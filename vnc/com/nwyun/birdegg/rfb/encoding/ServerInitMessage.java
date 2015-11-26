package com.nwyun.birdegg.rfb.encoding;

import com.nwyun.birdegg.transport.Reader;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class ServerInitMessage {
	private int frameBufferWidth;
	private int frameBufferHeight;
	private PixelFormat pixelFormat;
	private String name;

	public ServerInitMessage(Reader reader) {
		frameBufferWidth = reader.readUInt16();
		frameBufferHeight = reader.readUInt16();
		pixelFormat = new PixelFormat();
		pixelFormat.fill(reader);
		name = reader.readString();
	}

	public int getFrameBufferWidth() {
		return frameBufferWidth;
	}

	public int getFrameBufferHeight() {
		return frameBufferHeight;
	}

	public PixelFormat getPixelFormat() {
		return pixelFormat;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "ServerInitMessage: [name: " + name + ", framebuffer-width: "
				+ String.valueOf(frameBufferWidth) + ", framebuffer-height: "
				+ String.valueOf(frameBufferHeight) + ", server-pixel-format: "
				+ pixelFormat + "]";
	}
}
