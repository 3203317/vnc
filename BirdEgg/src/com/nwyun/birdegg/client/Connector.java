package com.nwyun.birdegg.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.glavsoft.rfb.IPasswordRetriever;
import com.glavsoft.rfb.protocol.Protocol;
import com.glavsoft.rfb.protocol.ProtocolSettings;
import com.glavsoft.transport.Reader;
import com.glavsoft.transport.Writer;
import com.nwyun.birdegg.lib.rdr.JavaInStream;
import com.nwyun.birdegg.lib.rdr.JavaOutStream;
import com.nwyun.birdegg.lib.rfb.CConnection;
import com.nwyun.birdegg.lib.rfb.CSecurity;
import com.nwyun.birdegg.lib.rfb.CSecurityNone;
import com.nwyun.birdegg.lib.rfb.CSecurityVncAuth;
import com.nwyun.birdegg.lib.rfb.Encodings;
import com.nwyun.birdegg.lib.rfb.SecTypes;
import com.nwyun.birdegg.lib.rfb.SecurityTypeException;
import com.nwyun.birdegg.lib.rfb.UserPasswdGetter;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class Connector extends CConnection implements UserPasswdGetter {
	final static LogWriter vlog = new LogWriter("Connector");

	private RfbServer _server;
	private Socket _socket;

	private JavaInStream _jis;
	private JavaOutStream _jos;

	private int currentEncoding;
	private int lastUsedEncoding;

	private boolean encodingChange;
	private boolean formatChange;

	public Connector(RfbServer server) {
		_server = server;
		// TODO
		currentEncoding = Encodings.ZRLE;
		lastUsedEncoding = Encodings.max;
		// TODO
		setShared(false);
		// TODO
		addSecType(SecTypes.none);
		addSecType(SecTypes.vncAuth);
		// TODO
		cp.supportsDesktopResize = true;
		cp.supportsLocalCursor = true;
	}

	protected Protocol workingProtocol;
	protected String connectionString;
	protected ProtocolSettings rfbSettings;

	public void connect() {
		try {
			_socket = new Socket(_server.getIp(), _server.getPort());
			// TODO
			setServerName(_socket.getInetAddress().getHostAddress() + "::"
					+ _socket.getPort());
			Reader reader = new Reader(_socket.getInputStream());
			Writer writer = new Writer(_socket.getOutputStream());
			// TODO
			PasswordChooser pc = new PasswordChooser(connectionString);
			rfbSettings = ProtocolSettings.getDefaultSettings();
			workingProtocol = new Protocol(reader, writer, pc, rfbSettings);
			String message = "Handshaking with remote host";
			workingProtocol.handshake();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void connect2() {
		try {
			_socket = new Socket(_server.getIp(), _server.getPort());
			// TODO
			setServerName(_socket.getInetAddress().getHostAddress() + "::"
					+ _socket.getPort());
			_jis = new JavaInStream(_socket.getInputStream());
			_jos = new JavaOutStream(_socket.getOutputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setStreams(_jis, _jos);
		initialiseProtocol();
		// TODO
		processMsg();
	}

	@Override
	public void processMsg() {
		while (true)
			super.processMsg();
	}

	@Override
	public CSecurity getCSecurity(int secType) {
		switch (secType) {
		case SecTypes.none:
			return new CSecurityNone();
		case SecTypes.vncAuth:
			return new CSecurityVncAuth(this);
		default:
			throw new SecurityTypeException();
		}
	}

	@Override
	public boolean getUserPasswd(StringBuffer user, StringBuffer password) {
		password.append(_server.getPassword());
		return true;
	}

	@Override
	public void serverInit() {
		super.serverInit();
		formatChange = encodingChange = true;
		requestNewUpdate();
	}

	private void requestNewUpdate() {
		if (formatChange) {
			PixelBufferImage im = new PixelBufferImage(150, 250);
			synchronized (this) {
				writer().writeSetPixelFormat(im.getPF());
			}
		}
		checkEncodings();
		synchronized (this) {
			writer().writeFramebufferUpdateRequest(0, 0, cp.width, cp.height,
					!formatChange);
		}
		formatChange = false;
	}

	private synchronized void checkEncodings() {
		if (encodingChange && state() == RFBSTATE_NORMAL) {
			vlog.info("Using " + Encodings.name(currentEncoding) + " encoding");
			writer().writeSetEncodings(currentEncoding, true);
			encodingChange = false;
		}
	}

	@Override
	public void setColourMapEntries(int firstColour, int nColours, int[] rgbs) {
		// TODO
		System.out.println("setColourMapEntries");
	}

	private class PasswordChooser implements IPasswordRetriever {
		private String connectionString;

		private PasswordChooser(String connectionString) {
			this.connectionString = connectionString;
		}

		@Override
		public String getPassword() {
			return "123222";
		}

	}
}
