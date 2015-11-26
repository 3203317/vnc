package com.nwyun.birdegg.rfb.encoding.decoder;

import com.nwyun.birdegg.exception.TransportException;
import com.nwyun.birdegg.rfb.encoding.EncodingType;
import com.nwyun.birdegg.transport.Reader;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class FramebufferUpdateRectangle {
	public int x;
	public int y;
	public int width;
	public int height;
	private EncodingType encodingType;

	public FramebufferUpdateRectangle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public EncodingType getEncodingType() {
		return encodingType;
	}

	public void fill(Reader reader) throws TransportException {
		x = reader.readUInt16();
		y = reader.readUInt16();
		width = reader.readUInt16();
		height = reader.readUInt16();
		int encoding = reader.readInt32();
		encodingType = EncodingType.byId(encoding);
	}
}
