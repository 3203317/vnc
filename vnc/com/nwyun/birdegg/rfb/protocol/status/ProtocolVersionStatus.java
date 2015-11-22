package com.nwyun.birdegg.rfb.protocol.status;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.nwyun.birdegg.exception.UnsupportedProtocolVersionException;
import com.nwyun.birdegg.rfb.protocol.ProtocolContext;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class ProtocolVersionStatus extends ProtocolStatus {
	private final Logger logger;
	public static final String PROTOCOL_VERSION_3_8 = "3.8";
	public static final String PROTOCOL_VERSION_3_7 = "3.7";
	public static final String PROTOCOL_VERSION_3_3 = "3.3";

	private static final int PROTOCOL_STRING_LENGTH = 12;
	private static final String PROTOCOL_STRING_REGEXP = "^RFB (\\d\\d\\d).(\\d\\d\\d)\n$";

	private static final int MIN_SUPPORTED_VERSION_MAJOR = 3;
	private static final int MIN_SUPPORTED_VERSION_MINOR = 3;

	private static final int MAX_SUPPORTED_VERSION_MAJOR = 3;
	private static final int MAX_SUPPORTED_VERSION_MINOR = 8;

	public ProtocolVersionStatus(ProtocolContext ctx) {
		super(ctx);
		logger = Logger.getLogger(getClass().getName());
	}

	@Override
	public void execute() {
		String _protocolString = reader.readString(PROTOCOL_STRING_LENGTH);
		logger.info("Server sent protocol string: "
				+ _protocolString.substring(0, _protocolString.length() - 1));

		Pattern _pattern = Pattern.compile(PROTOCOL_STRING_REGEXP);
		final Matcher _matcher = _pattern.matcher(_protocolString);

		if (!_matcher.matches())
			throw new UnsupportedProtocolVersionException(
					"Unsupported protocol version: " + _protocolString);

		int _major = Integer.parseInt(_matcher.group(1));
		int _minor = Integer.parseInt(_matcher.group(2));

		if (_major < MAX_SUPPORTED_VERSION_MAJOR)
			throw new UnsupportedProtocolVersionException(
					"Unsupported protocol version: " + _major + "." + _minor);

		if (_minor < MAX_SUPPORTED_VERSION_MINOR)
			throw new UnsupportedProtocolVersionException(
					"Unsupported protocol version: " + _major + "." + _minor);

		writer.write(("RFB 00" + _major + ".00" + _minor + "\n").getBytes());
		logger.info("Set protocol version to: " + _protocolString.trim());
		ctx.setProtocolVersion(_major + "." + _minor);
		changeStatusTo(new SecurityTypeStatus(ctx));
	}
}
