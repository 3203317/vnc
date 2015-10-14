package net.foreworld.vncviewer;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

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

	private Socket _socket;

	public NwDesk(String ip, int port, String password) {
		_ip = ip;
		_port = port;
		_password = password;
	}

	public void init() throws UnknownHostException, IOException {
		_socket = new Socket(_ip, _port);
		vlog.info("connected to host " + _ip + " listening on port " + _port);
	}

	@Override
	public void start() throws Exception {
		init();
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
