package net.foreworld.vncviewer.rdr;

import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
public class OutStream {

	private static final int _defaultBufSize = 16384;

	private OutputStream _os;
	private int _bufSize;
	protected byte[] _b;

	public OutStream(OutputStream is, int bufSize) {
		_os = is;
		_bufSize = bufSize;
		_b = new byte[bufSize];
	}

	public OutStream(OutputStream is) {
		this(is, _defaultBufSize);
	}

	public void close() throws IOException {
		if (null != _os) {
			_os.close();
		}
	}
}
