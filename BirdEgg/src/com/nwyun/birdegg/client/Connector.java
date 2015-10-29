package com.nwyun.birdegg.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

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

	public Connector(RfbServer server) {
		_server = server;
		// TODO
		currentEncoding = Encodings.ZRLE;
		lastUsedEncoding = Encodings.max;
		addSecType(SecTypes.none);
		addSecType(SecTypes.vncAuth);
	}

	public void connect() {
		try {
			_socket = new Socket(_server.getIp(), _server.getPort());
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
		encodingChange = true;
		requestNewUpdate();
	}

	private void requestNewUpdate() {
		checkEncodings();
	}

	private synchronized void checkEncodings() {
		if (encodingChange && state() == RFBSTATE_NORMAL) {
			vlog.info("Using " + Encodings.name(currentEncoding) + " encoding");
			writer().writeSetEncodings(currentEncoding, true);
			encodingChange = false;
		}
	}
}
