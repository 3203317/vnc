package net.foreworld.nw.rdr;

import java.io.InputStream;

import net.foreworld.nw.exception.EndOfStream;
import net.foreworld.nw.exception.IOException;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
public class SmartInStream extends InStream {

	private final static int _defaultBufSize = 1024 * 8;
	private final static int _minBulkSize = 1024;

	private InputStream _is;
	private int _bufSize;
	protected byte[] _b;

	public SmartInStream(InputStream is, int bufSize) {
		_is = is;
		_bufSize = bufSize;
		_b = new byte[bufSize];
	}

	public SmartInStream(InputStream is) {
		this(is, _defaultBufSize);
	}

	public void readBytes(byte[] data, int offset, int len) {
		if (len < _minBulkSize) {
			super.readBytes(data, offset, len);
			return;
		}
		// TODO
		read(data, offset, len);
	}

	private int read(byte[] buf, int offset, int len) {
		try {
			int n = _is.read(buf, offset, len);
			if (0 > n) {
				throw new EndOfStream();
			}
			return n;
		} catch (java.io.IOException e) {
			throw new IOException(e);
		}
	}

	public void close() throws java.io.IOException {
		if (null != _is) {
			_is.close();
			_is = null;
		}
	}

}
