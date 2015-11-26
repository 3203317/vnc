package com.nwyun.birdegg.rfb.encoding.decoder;

import com.nwyun.birdegg.drawing.Renderer;
import com.nwyun.birdegg.exception.TransportException;
import com.nwyun.birdegg.transport.Reader;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public abstract class Decoder {
	/**
	 * Decode rectangle data
	 * 
	 * @param reader
	 * @param renderer
	 * @param rect
	 * @throws TransportException
	 */
	abstract public void decode(Reader reader, Renderer renderer,
			FramebufferUpdateRectangle rect) throws TransportException;

	public void reset() {
		/* empty */
	}
}
