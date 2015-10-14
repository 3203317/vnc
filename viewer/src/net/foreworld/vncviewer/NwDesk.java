package net.foreworld.vncviewer;

import java.io.IOException;
import java.net.UnknownHostException;

import net.foreworld.vncviewer.rfb.Connection;
import net.foreworld.vncviewer.rfb.LogWriter;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
public class NwDesk implements YunDesk {

	static LogWriter vlog = new LogWriter("NwDesk");

	private String _ip;
	private int _port;
	private String _password;

	private Connection _conn;

	public NwDesk(String ip, int port, String password) {
		_ip = ip;
		_port = port;
		_password = password;
	}

	public void init() throws UnknownHostException, IOException {
		_conn = new Connection(_ip, _port, _password);
		_conn.init();
	}

	@Override
	public void start() throws Exception {
		init();
		while (true) {
			_conn.processMsg();
		}
	}

	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void restart() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void lock() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void logout() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void switchUser() throws Exception {
		// TODO Auto-generated method stub

	}

}
