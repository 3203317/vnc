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
		_os = new DataOutputStream(os);
	}

	public void write(byte[] b) {
		write(b, 0, b.length);
	}

	public void write(byte[] b, int offset, int length)
			throws TransportException {
		try {
			_os.write(b, offset, length <= b.length ? length : b.length);
		} catch (IOException e) {
			throw new TransportException("cannot write " + length + " bytes", e);
		}
	}
}
