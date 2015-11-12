package com.nwyun.birdegg.rfb.protocol.status;

import java.util.logging.Logger;

import com.nwyun.birdegg.exception.VersionStatusException;
import com.nwyun.birdegg.rfb.protocol.ProtocolContext;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class VersionStatus extends ProtocolStatus {
	private final Logger _logger;
	public static final String PROTOCOL_VERSION_3_8 = "3.8";
	public static final String PROTOCOL_VERSION_3_7 = "3.7";
	public static final String PROTOCOL_VERSION_3_3 = "3.3";

	private static final int PROTOCOL_STRING_LENGTH = 12;
	private static final String PROTOCOL_STRING_REGEXP = "^RFB (\\d\\d\\d).(\\d\\d\\d)\n$";

	private static final int MIN_SUPPORTED_VERSION_MAJOR = 3;
	private static final int MIN_SUPPORTED_VERSION_MINOR = 3;

	private static final int MAX_SUPPORTED_VERSION_MAJOR = 3;
	private static final int MAX_SUPPORTED_VERSION_MINOR = 8;

	public VersionStatus(ProtocolContext ctx) {
		super(ctx);
		_logger = Logger.getLogger(getClass().getName());
	}

	@Override
	public void next() throws VersionStatusException {
		String protocolString = reader.readString(PROTOCOL_STRING_LENGTH);
		_logger.info("set protocol version to: " + protocolString);
		changeStateTo(new SecurityTypeStatus(ctx));
	}
}
