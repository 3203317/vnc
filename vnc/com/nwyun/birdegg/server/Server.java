package com.nwyun.birdegg.server;

import com.nwyun.birdegg.rfb.encoding.PixelFormat;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public abstract class Server {
	private String ip;
	private int port;
	private String password;
	private int width;
	private int height;
	private String name;
	private Version version;
	private PixelFormat pixelFormat;

	public PixelFormat getPixelFormat() {
		return pixelFormat;
	}

	public void setPixelFormat(PixelFormat pixelFormat) {
		this.pixelFormat = pixelFormat;
	}

	public Version getVersion() {
		return version;
	}

	public void setVersion(Version version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Server(String ip, int port, String password) {
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
