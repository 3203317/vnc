package com.nwyun.birdegg.rfb.encoding.decoder;

import com.nwyun.birdegg.drawing.Renderer;
import com.nwyun.birdegg.exception.TransportException;
import com.nwyun.birdegg.transport.Reader;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class RawDecoder extends Decoder {
	private static RawDecoder instance = new RawDecoder();

	public static RawDecoder getInstance() {
		return instance;
	}

	private RawDecoder() { /* empty */
	}

	@Override
	public void decode(Reader reader, Renderer renderer,
			FramebufferUpdateRectangle rect) throws TransportException {
		decode(reader, renderer, rect.x, rect.y, rect.width, rect.height);
	}

	public void decode(Reader reader, Renderer renderer, int x, int y,
			int width, int height) throws TransportException {
		int length = width * height * renderer.getBytesPerPixel();
		byte[] bytes = ByteBuffer.getInstance().getBuffer(length);
		reader.readBytes(bytes, 0, length);
		renderer.drawBytes(bytes, x, y, width, height);
	}
}
