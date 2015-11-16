package com.nwyun.birdegg.transport;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.nwyun.birdegg.exception.TransportException;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class Writer {
	private final DataOutputStream _os;

	public Writer(OutputStream os) {
		this._os = new DataOutputStream(os);
	}

	public void flush() throws TransportException {
		try {
			_os.flush();
		} catch (IOException e) {
			throw new TransportException("Cannot flush output stream", e);
		}
	}

	public void writeByte(int b) throws TransportException {
		write((byte) (b & 0xff));
	}

	public void write(byte b) throws TransportException {
		try {
			_os.writeByte(b);
		} catch (IOException e) {
			throw new TransportException("Cannot write byte", e);
		}
	}

	public void writeInt16(int sh) throws TransportException {
		write((short) (sh & 0xffff));
	}

	public void write(short sh) throws TransportException {
		try {
			_os.writeShort(sh);
		} catch (IOException e) {
			throw new TransportException("Cannot write short", e);
		}
	}

	public void writeInt32(int i) throws TransportException {
		write(i);
	}

	public void writeInt64(long i) throws TransportException {
		try {
			_os.writeLong(i);
		} catch (IOException e) {
			throw new TransportException("Cannot write long", e);
		}
	}

	public void write(int i) throws TransportException {
		try {
			_os.writeInt(i);
		} catch (IOException e) {
			throw new TransportException("Cannot write int", e);
		}
	}

	public void write(byte[] b) throws TransportException {
		write(b, 0, b.length);
	}

	public void write(byte[] b, int length) throws TransportException {
		write(b, 0, length);
	}

	public void write(byte[] b, int offset, int length)
			throws TransportException {
		try {
			_os.write(b, offset, length <= b.length ? length : b.length);
		} catch (IOException e) {
			throw new TransportException("Cannot write " + length + " bytes", e);
		}
	}
}
