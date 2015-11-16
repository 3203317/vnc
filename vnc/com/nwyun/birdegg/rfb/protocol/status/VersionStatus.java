package com.nwyun.birdegg.rfb.protocol.status;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		_logger.info("server sent protocol string: "
				+ protocolString.substring(0, protocolString.length() - 1));

		Pattern pattern = Pattern.compile(PROTOCOL_STRING_REGEXP);
		final Matcher matcher = pattern.matcher(protocolString);

		if (!matcher.matches())
			throw new VersionStatusException("Unsupported protocol version: "
					+ protocolString);

		int major = Integer.parseInt(matcher.group(1));
		int minor = Integer.parseInt(matcher.group(2));

		if (major < MAX_SUPPORTED_VERSION_MAJOR)
			throw new VersionStatusException("Unsupported protocol version: "
					+ major + "." + minor);

		if (minor < MAX_SUPPORTED_VERSION_MINOR)
			throw new VersionStatusException("Unsupported protocol version: "
					+ major + "." + minor);

		writer.write(("RFB 00" + major + ".00" + minor + "\n").getBytes());
		_logger.info("Set protocol version to: " + protocolString);
		server.setVersion(server.new Version(major, minor));
		changeStateTo(new SecurityTypeStatus(ctx));
	}
}
