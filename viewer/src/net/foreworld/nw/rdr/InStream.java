package net.foreworld.nw.rdr;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
public class InStream {

	private final static int _defaultBufSize = 8192;

	private InputStream _is;
	private int _bufSize;
	protected byte[] _b;

	public InStream(InputStream is, int bufSize) {
		_is = is;
		_bufSize = bufSize;
		_b = new byte[bufSize];
	}

	public InStream(InputStream is) {
		this(is, _defaultBufSize);
	}

	public void close() throws IOException {
		if (null != _is) {
			_is.close();
			_is = null;
		}
	}

	public void readBytes(byte[] data, int offset, int len) throws IOException {
		// TODO
		read(data, offset, len);
	}

	private int read(byte[] buf, int offset, int len) throws IOException {
		int n = _is.read(buf, offset, len);
		return n;
	}
}
