package com.nwyun.birdegg.client;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public abstract class RfbServer {

	private String ip;
	private int port;
	private String password;

	public RfbServer(String ip, int port, String password) {
		this.ip = ip;
		this.port = port;
		this.password = password;
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
}
