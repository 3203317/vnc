package net.foreworld.nw.rfb;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
abstract public class RfbServer {
	public class Version {
		private int major;
		private int minor;

		public Version(int major, int minor) {
			this.major = major;
			this.minor = minor;
		}

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

	private String ip;
	private int port;
	private String password;

	private Version version;

	public RfbServer(String ip, int port, String password) {
		this.ip = ip;
		this.port = port;
		this.password = password;
	}

	public String getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}

	public String getPassword() {
		return password;
	}

	public Version getVersion() {
		return version;
	}

	public void setVersion(Version version) {
		this.version = version;
	}

	protected void start() {
		// TODO
	}

	protected void close() {
		// TODO
	}

	protected void restart() {
		// TODO
	}

	protected void lock() {
		// TODO
	}

	protected void logout() {
		// TODO
	}

	protected void switchUser() {
		// TODO
	}
}
