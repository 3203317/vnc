package net.foreworld.nw.rfb;

import java.io.IOException;

import net.foreworld.nw.rdr.InStream;
import net.foreworld.nw.rdr.OutStream;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
public class RbfServer {

	public class Version {
		private int major;
		private int minor;

		public int getMajor() {
			return major;
		}

		public void setMajor(int major) {
			this.major = major;
		}

		public int getMinor() {
			return minor;
		}

		public void setMinor(int minor) {
			this.minor = minor;
		}
	}

	private Version _version;
	private InStream _is;
	private OutStream _os;

	public RbfServer(InStream is, OutStream os) {
		_is = is;
		_os = os;
	}

	public Version readVersion() throws IOException {
		if (null != _version) {
			return _version;
		}
		byte[] b = new byte[12];
		_is.readBytes(b, 0, 12);
		return null;
	}

}
