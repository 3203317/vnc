package net.foreworld.nw;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
public abstract class RfbServer {

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

	protected Version readVersion() {
		return null;
	}
}
