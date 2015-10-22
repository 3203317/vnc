package net.foreworld.nw.rfb;

import java.io.IOException;
import java.net.Socket;

import net.foreworld.nw.exception.CloseSocketException;
import net.foreworld.nw.exception.ConnectServerException;
import net.foreworld.nw.exception.ProcessMsgDefaultException;
import net.foreworld.nw.exception.ServerVersionException;
import net.foreworld.nw.rdr.InStream;
import net.foreworld.nw.rdr.OutStream;
import net.foreworld.nw.rdr.SmartInStream;
import net.foreworld.nw.rdr.SmartOutStream;
import net.foreworld.nw.rfb.RfbServer.Version;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
public class Connector {
	final static LogWriter vlog = new LogWriter("Connector");

	public final static int RFBSTATE_UNINITIALISED = 0;
	public final static int RFBSTATE_PROTOCOL_VERSION = 1;
	public final static int RFBSTATE_SECURITY_TYPES = 2;
	public final static int RFBSTATE_SECURITY = 3;
	public final static int RFBSTATE_SECURITY_RESULT = 4;
	public final static int RFBSTATE_INITIALISATION = 5;
	public final static int RFBSTATE_NORMAL = 6;
	public final static int RFBSTATE_INVALID = 7;

	private int _state;

	private Socket _socket;
	private InStream _is;
	private OutStream _os;

	private RfbServer _server;
	private boolean _isRun;

	public Connector(RfbServer server) {
		_server = server;
		_isRun = true;
		_state = RFBSTATE_UNINITIALISED;
	}

	public void connect() {
		try {
			_socket = new Socket(_server.getIp(), _server.getPort());
			vlog.info("connected to host " + _server.getIp()
					+ " listening on port " + _server.getPort() + ".");
			// stream
			_is = new SmartInStream(_socket.getInputStream());
			_os = new SmartOutStream(_socket.getOutputStream());
		} catch (Exception e) {
			throw new ConnectServerException();
		}
		// change state
		_state = RFBSTATE_PROTOCOL_VERSION;
		processMsg();
	}

	private void processMsg() {
		while (_isRun) {
			switch (_state) {
			case RFBSTATE_PROTOCOL_VERSION:
				processVersionMsg();
				break;
			case RFBSTATE_SECURITY_TYPES:
				processSecurityTypesMsg();
				break;
			default:
				throw new ProcessMsgDefaultException();
			}
		}
	}

	public void close() {
		_isRun = false;
		// TODO
		if (null != _socket) {
			if (null != _is)
				_is.close();

			if (null != _os)
				_os.close();

			try {
				_socket.close();
			} catch (IOException e) {
				throw new CloseSocketException();
			}
			_socket = null;
		}
	}

	private void processVersionMsg() {
		vlog.info("processVersionMsg() starting.");
		readVersion(_is);

		_state = RFBSTATE_SECURITY_TYPES;
		vlog.info("Using RFB Protocol version "
				+ _server.getVersion().getMajor() + "."
				+ _server.getVersion().getMinor() + ".");
	}

	private void processSecurityTypesMsg() {
		vlog.info("processSecurityTypesMsg() starting.");

		int secType = SecTypes.invalid;

		Version ver = _server.getVersion();

		if (3 == ver.getMajor() && 3 == ver.getMinor()) {
			// TODO
		} else {
			// TODO
		}
	}

	private void readVersion(InStream is) {
		byte[] b = new byte[12];
		is.readBytes(b, 0, 12);

		if (('R' != b[0]) || ('F' != b[1]) || ('B' != b[2]) || (' ' != b[3])
				|| ('0' > b[4]) || ('9' < b[4]) || ('0' > b[5]) || ('9' < b[5])
				|| ('0' > b[6]) || ('9' < b[6]) || ('.' != b[7])
				|| ('0' > b[8]) || ('9' < b[8]) || ('0' > b[9]) || ('9' < b[9])
				|| ('0' > b[10]) || ('9' < b[10]) || ('\n' != b[11])) {
			throw new ServerVersionException();
		}

		int major = 100 * (b[4] - '0') + 10 * (b[5] - '0') + (b[6] - '0');
		int minor = 100 * (b[8] - '0') + 10 * (b[9] - '0') + (b[10] - '0');

		_server.setVersion(_server.new Version(major, minor));
	}
}
