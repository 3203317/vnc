package com.nwyun.birdegg.rfb.client;

import com.nwyun.birdegg.exception.TransportException;
import com.nwyun.birdegg.rfb.encoding.PixelFormat;
import com.nwyun.birdegg.transport.Writer;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class SetPixelFormatMessage implements ClientToServerMessage {
	private final PixelFormat pixelFormat;

	public SetPixelFormatMessage(PixelFormat pixelFormat) {
		this.pixelFormat = pixelFormat;
	}

	@Override
	public void send(Writer writer) throws TransportException {
		writer.writeByte(SET_PIXEL_FORMAT);
		writer.writeInt16(0);
		writer.writeByte(0);
		pixelFormat.send(writer);
		writer.flush();
	}
}
