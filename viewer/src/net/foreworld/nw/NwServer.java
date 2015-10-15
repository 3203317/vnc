package net.foreworld.nw;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import net.foreworld.nw.listener.OnShowListener;
import net.foreworld.nw.rfb.Connection;
import net.foreworld.nw.rfb.LogWriter;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
public class NwServer {
	final static LogWriter vlog = new LogWriter("NwServer");

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

	private String _ip;
	private int _port;
	private String _password;
	private Version version;

	private Connection _conn;
	private ArrayList<OnShowListener> _listener;

	public NwServer(String ip, int port, String password) {
		_ip = ip;
		_port = port;
		_password = password;
		_listener = new ArrayList<OnShowListener>();
		_conn = new Connection(this, _ip, _port, _password);
	}

	public Version getVersion() {
		return version;
	}

	public void setVersion(Version version) {
		this.version = version;
	}

	/**
	 * 连接鸟窝服务器
	 *
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public void start() throws Exception {
		_conn.init();
		_conn.processMsg();
	}

	public void close() throws IOException {
		_conn.close();
	}

	public void restart() {
		// TODO
	}

	public void lock() {
		// TODO
	}

	public void logout() {
		// TODO
	}

	public void switchUser() {
		// TODO
	}

	public void setOnShowListener(OnShowListener listener) {
		_listener.add(listener);
	}
}
