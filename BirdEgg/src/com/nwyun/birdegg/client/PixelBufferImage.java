package com.nwyun.birdegg.client;

import com.nwyun.birdegg.lib.rfb.PixelBuffer;
import com.nwyun.birdegg.lib.rfb.PixelFormat;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class PixelBufferImage extends PixelBuffer {
	public PixelBufferImage(int w, int h) {
		setPF(new PixelFormat(8, 8, false, false, 0, 0, 0, 0, 0, 0));
		// TODO
	}

	byte[] reds;
	byte[] greens;
	byte[] blues;

	static LogWriter vlog = new LogWriter("PixelBufferImage");
}
