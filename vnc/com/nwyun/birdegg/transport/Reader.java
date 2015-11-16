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

	public int readUInt8() throws TransportException {
		return readByte() & 0x0ff;
	}

	public byte readByte() throws TransportException {
		try {
			byte readByte = _is.readByte();
			return readByte;
		} catch (EOFException e) {
			throw new ClosedConnectionException(e);
		} catch (IOException e) {
			throw new TransportException("Cannot read byte", e);
		}
	}

	public int readInt32() throws TransportException {
		try {
			int readInt = _is.readInt();
			return readInt;
		} catch (EOFException e) {
			throw new ClosedConnectionException(e);
		} catch (IOException e) {
			throw new TransportException("Cannot read int32", e);
		}
	}

	public String readString() throws TransportException {
		// unset most significant (sign) bit 'cause InputStream#readFully reads
		// [int] length bytes from stream. Change when really need read string
		// more than 2147483647 bytes length
		int length = readInt32() & Integer.MAX_VALUE;
		return readString(length);
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
