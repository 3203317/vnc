package com.nwyun.birdegg.rfb.encoding.decoder;

import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

import com.nwyun.birdegg.drawing.Renderer;
import com.nwyun.birdegg.exception.TransportException;
import com.nwyun.birdegg.transport.Reader;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class ZlibDecoder extends Decoder {
	private Inflater decoder;

	@Override
	public void decode(Reader reader, Renderer renderer,
			FramebufferUpdateRectangle rect) throws TransportException {
		// TODO
	}

	protected byte[] unzip(Reader reader, int zippedLength, int length)
			throws TransportException {
		byte[] bytes = ByteBuffer.getInstance()
				.getBuffer(zippedLength + length);
		reader.readBytes(bytes, 0, zippedLength);
		if (null == decoder) {
			decoder = new Inflater();
		}
		decoder.setInput(bytes, 0, zippedLength);
		try {
			decoder.inflate(bytes, zippedLength, length);
		} catch (DataFormatException e) {
			throw new TransportException("Cannot inflate Zlib data", e);
		}
		return bytes;
	}

	@Override
	public void reset() {
		decoder = null;
	}
}
