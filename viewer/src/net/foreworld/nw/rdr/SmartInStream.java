package net.foreworld.nw.rdr;

import java.io.IOException;
import java.io.InputStream;

import net.foreworld.nw.exception.CloseSocketException;
import net.foreworld.nw.exception.ReadInputStreamException;

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
		// TODO
		if (len < _minBulkSize) {
			super.readBytes(data, offset, len);
			return;
		}
		read(data, offset, len);
	}

	private int read(byte[] buf, int offset, int len) {
		int n;
		try {
			n = _is.read(buf, offset, len);
		} catch (IOException e) {
			throw new ReadInputStreamException();
		}
		return n;
	}

	@Override
	public void close() {
		if (null != _is) {
			try {
				_is.close();
			} catch (Exception e) {
				throw new CloseSocketException();
			}
			_is = null;
		}
	}
}
