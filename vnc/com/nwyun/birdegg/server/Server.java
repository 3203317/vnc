package com.nwyun.birdegg.server;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public abstract class Server {
	private String ip;
	private int port;
	private String password;
	private String name;
	private String version;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}
