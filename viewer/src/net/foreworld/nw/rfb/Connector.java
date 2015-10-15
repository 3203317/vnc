package net.foreworld.nw.rfb;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import net.foreworld.nw.NwServer;
import net.foreworld.nw.rdr.InStream;
import net.foreworld.nw.rdr.OutStream;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
public class Connector {
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

	private NwServer _server;

	public Connector(NwServer server, String ip, int port, String password) {
		_server = server;
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

		_state = RFBSTATE_PROTOCOL_VERSION;
	}

	public void processMsg() throws Exception {
		while (true) {
			switch (_state) {
			case RFBSTATE_PROTOCOL_VERSION:
				processVersionMsg();
				break;
			case RFBSTATE_SECURITY_TYPES:
				processSecurityTypesMsg();
				break;
			default:
				throw new Exception("Connection.processMsg(): invalid state.");
			}
		}
	}

	public void close() throws IOException {
		if (null != _socket) {
			if (null != _is)
				_is.close();

			if (null != _os)
				_os.close();

			_socket.close();
			_socket = null;
		}
	}

	private void processVersionMsg() throws IOException {
		vlog.info("processVersionMsg() started.");

		// byte[] b = new byte[12];
		// _is.readBytes(b, 0, 12);
		_server.setVersion(_server.new Version(3, 7));
		_state = RFBSTATE_SECURITY_TYPES;
	}

	private void processSecurityTypesMsg() {
		System.out.println(_server.getVersion().getMajor() + "."
				+ _server.getVersion().getMinor());
	}

}
