package com.nwyun.birdegg.rfb.client;

import com.nwyun.birdegg.exception.TransportException;
import com.nwyun.birdegg.transport.Writer;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class FramebufferUpdateRequestMessage implements ClientToServerMessage {
	private final boolean incremental;
	private final int height;
	private final int width;
	private final int x;
	private final int y;

	public FramebufferUpdateRequestMessage(int x, int y, int width, int height,
			boolean incremental) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.incremental = incremental;
	}

	@Override
	public void send(Writer writer) throws TransportException {
		writer.writeByte(FRAMEBUFFER_UPDATE_REQUEST);
		writer.writeByte(incremental ? 1 : 0);
		writer.writeInt16(x);
		writer.writeInt16(y);
		writer.writeInt16(width);
		writer.writeInt16(height);
		writer.flush();
	}
}