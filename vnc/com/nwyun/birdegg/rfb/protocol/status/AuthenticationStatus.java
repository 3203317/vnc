package com.nwyun.birdegg.rfb.protocol.status;

import java.util.logging.Logger;

import com.glavsoft.exceptions.TransportException;
import com.nwyun.birdegg.exception.AuthenticationFailedException;
import com.nwyun.birdegg.exception.ClosedConnectionException;
import com.nwyun.birdegg.rfb.protocol.ProtocolContext;
import com.nwyun.birdegg.rfb.protocol.auth.AuthHandler;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class AuthenticationStatus extends ProtocolStatus {
	private final Logger _logger;

	public AuthenticationStatus(ProtocolContext ctx, AuthHandler authHandler) {
		super(ctx);
		_logger = Logger.getLogger(getClass().getName());
		_authHandler = authHandler;
	}

	private static final int AUTH_RESULT_OK = 0;
	private static final int AUTH_RESULT_FAILED = 1;
	private static final int AUTH_RESULT_TOO_MANY = 2;
	private final AuthHandler _authHandler;

	@Override
	public void next() {
		_authHandler.authenticate(reader, writer,
				ctx.getSettings().authCapabilities, ctx.getPasswordNeed());
		// skip when protocol < 3.8 and NONE_AUTH
		if (true) {
			checkSecurityResult();
		}

		_logger.info("Server authenticate success");
		changeStateTo(new InitStatus(ctx));
	}

	/**
	 * Check Security Result received from server May be: * 0 - OK * 1 - Failed
	 * 
	 * @throws TransportException
	 * @throws AuthenticationFailedException
	 */
	protected void checkSecurityResult() {
		if (reader.readInt32() != AUTH_RESULT_OK) {
			try {
				String reason = reader.readString();
				throw new AuthenticationFailedException(reason);
			} catch (ClosedConnectionException e) {
				// protocol version 3.3 and 3.7 does not send reason string,
				// but silently closes the connection
				throw new AuthenticationFailedException("Authentication failed");
			}
		}
	}
}
