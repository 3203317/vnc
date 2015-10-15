package net.foreworld.nw;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import net.foreworld.nw.listener.OnShowListener;
import net.foreworld.nw.rfb.Connector;
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

	private Connector _connector;
	private ArrayList<OnShowListener> _listeners;

	public NwServer(String ip, int port, String password) {
		_ip = ip;
		_port = port;
		_password = password;
		_listeners = new ArrayList<OnShowListener>();
		_connector = new Connector(this, _ip, _port, _password);
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
		_connector.init();
		_connector.processMsg();
	}

	public void close() throws IOException {
		_connector.close();
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
		_listeners.add(listener);
	}
}
