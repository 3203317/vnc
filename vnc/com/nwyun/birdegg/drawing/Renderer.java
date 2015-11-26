package com.nwyun.birdegg.drawing;

import java.util.Arrays;

import com.nwyun.birdegg.rfb.encoding.PixelFormat;
import com.nwyun.birdegg.rfb.encoding.decoder.FramebufferUpdateRectangle;
import com.nwyun.birdegg.transport.Reader;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public abstract class Renderer {
	protected Reader reader;
	private final Object lock = new Object();

	public abstract void drawJpegImage(byte[] bytes, int offset,
			int jpegBufferLength, FramebufferUpdateRectangle rect);

	protected int width;
	protected int height;
	protected int[] pixels;
	public PixelFormat pixelFormat;

	protected void init(Reader reader, int width, int height,
			PixelFormat pixelFormat) {
		this.reader = reader;
		this.width = width;
		this.height = height;
		initPixelFormat(pixelFormat);
		pixels = new int[width * height];
		Arrays.fill(pixels, 0);
	}

	public void initPixelFormat(PixelFormat pixelFormat) {
		synchronized (lock) {
			this.pixelFormat = pixelFormat;
		}
	}

	public Object getLock() {
		return lock;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
