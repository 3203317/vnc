package net.foreworld.nw.rdr;

import java.io.IOException;
import java.io.OutputStream;

import net.foreworld.nw.exception.CloseSocketException;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
abstract public class OutStream {

	private final static int _defaultBufSize = 1024 * 8 * 2;

	private OutputStream _os;
	private int _bufSize;
	protected byte[] _b;

	public OutStream(OutputStream os, int bufSize) {
		_os = os;
		_bufSize = bufSize;
		_b = new byte[bufSize];
	}

	public OutStream(OutputStream is) {
		this(is, _defaultBufSize);
	}

	public void close() {
		if (null != _os) {
			try {
				_os.close();
			} catch (IOException e) {
				throw new CloseSocketException();
			}
			_os = null;
		}
	}
}
