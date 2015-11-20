package com.nwyun.birdegg.rfb.client;

import com.nwyun.birdegg.exception.TransportException;
import com.nwyun.birdegg.transport.Writer;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public interface ClientToServerMessage {
	byte SET_PIXEL_FORMAT = 0;
	byte SET_ENCODINGS = 2;
	byte FRAMEBUFFER_UPDATE_REQUEST = 3;
	byte KEY_EVENT = 4;
	byte POINTER_EVENT = 5;
	byte CLIENT_CUT_TEXT = 6;

	void send(Writer writer) throws TransportException;
}
