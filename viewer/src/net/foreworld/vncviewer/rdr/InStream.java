package net.foreworld.vncviewer.rdr;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
public class InStream {

	private static final int _defaultBufSize = 8192;

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
		}
	}
}
