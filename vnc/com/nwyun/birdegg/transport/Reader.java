package com.nwyun.birdegg.transport;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

import com.nwyun.birdegg.exception.ClosedConnectionException;
import com.nwyun.birdegg.exception.TransportException;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class Reader {
	private final DataInputStream _is;

	public Reader(InputStream is) {
		_is = new DataInputStream(new BufferedInputStream(is));
	}

	public String readString(int length) {
		return new String(readBytes(length));
	}

	public byte[] readBytes(int length) {
		byte b[] = new byte[length];
		return readBytes(b, 0, length);
	}

	public byte[] readBytes(byte[] b, int offset, int length)
			throws TransportException {
		try {
			_is.readFully(b, offset, length);
			return b;
		} catch (EOFException e) {
			throw new ClosedConnectionException(e);
		} catch (IOException e) {
			throw new TransportException("Cannot read " + length
					+ " bytes array", e);
		}
	}
}
