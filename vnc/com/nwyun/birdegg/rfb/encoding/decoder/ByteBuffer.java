package com.nwyun.birdegg.rfb.encoding.decoder;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class ByteBuffer {
	private static ByteBuffer instance = new ByteBuffer();
	private byte[] buffer = new byte[0];

	private ByteBuffer() { /* empty */
	}

	public static ByteBuffer getInstance() {
		return instance;
	}

	public void correctBufferCapacity(int length) {
		assert (buffer != null);
		if (buffer.length < length)
			buffer = new byte[length];
	}

	public byte[] getBuffer(int length) {
		correctBufferCapacity(length);
		return buffer;
	}
}
