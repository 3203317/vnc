package net.foreworld.vncviewer.rfb;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import net.foreworld.vncviewer.rdr.InStream;
import net.foreworld.vncviewer.rdr.OutStream;
import net.foreworld.vncviewer.rfb.RbfServer.Version;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
public class Connection {
	final static LogWriter vlog = new LogWriter("Connection");

	public final static int RFBSTATE_UNINITIALISED = 0;
	public final static int RFBSTATE_PROTOCOL_VERSION = 1;
	public final static int RFBSTATE_SECURITY_TYPES = 2;
	public final static int RFBSTATE_SECURITY = 3;
	public final static int RFBSTATE_SECURITY_RESULT = 4;
	public final static int RFBSTATE_INITIALISATION = 5;
	public final static int RFBSTATE_NORMAL = 6;
	public final static int RFBSTATE_INVALID = 7;

	private String _ip;
	private int _port;
	private String _password;
	private int _state;

	private Socket _socket;
	private InStream _is;
	private OutStream _os;

	private RbfServer _server;

	public Connection(String ip, int port, String password) {
		_ip = ip;
		_port = port;
		_password = password;
		_state = RFBSTATE_UNINITIALISED;
	}

	public void init() throws UnknownHostException, IOException {
		_socket = new Socket(_ip, _port);
		vlog.info("connected to host " + _ip + " listening on port " + _port
				+ ".");

		_is = new InStream(_socket.getInputStream());
		_os = new OutStream(_socket.getOutputStream());
		_server = new RbfServer(_is, _os);

		_state = RFBSTATE_PROTOCOL_VERSION;
	}

	public void processMsg() throws Exception {
		switch (_state) {
		case RFBSTATE_PROTOCOL_VERSION:
			processVersionMsg();
			break;
		default:
			throw new Exception("Connection.processMsg(): invalid state.");
		}
	}

	public void close() throws IOException {
		if (null != _socket) {
			if (null != _is) {
				_is.close();
			}
			if (null != _os) {
				_os.close();
			}
			_socket.close();
		}
	}

	private void processVersionMsg() {
		vlog.info("processVersionMsg() started.");
		Version version = _server.readVersion();
	}

}
