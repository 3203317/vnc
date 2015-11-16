package com.nwyun.birdegg.rfb.protocol.status;

import java.util.logging.Logger;

import com.nwyun.birdegg.exception.SecurityTypeStatusException;
import com.nwyun.birdegg.rfb.CapabilityContainer;
import com.nwyun.birdegg.rfb.protocol.ProtocolContext;
import com.nwyun.birdegg.util.Strings;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class SecurityTypeStatus extends ProtocolStatus {
	private final Logger _logger;

	public SecurityTypeStatus(ProtocolContext ctx) {
		super(ctx);
		_logger = Logger.getLogger(getClass().getName());
	}

	@Override
	public void next() {
		int secTypesNum = reader.readUInt8();
		if (0 == secTypesNum) {
			throw new SecurityTypeStatusException(reader.readString());
		}

		byte[] secTypes = reader.readBytes(secTypesNum);
		_logger.info("security types received (" + secTypesNum + "): "
				+ Strings.toString(secTypes));

		selectAuthHandler(secTypes, ctx.getSettings().authCapabilities);
	}

	private void selectAuthHandler(byte[] secTypes,
			CapabilityContainer authCapabilities) {
		for (byte type : secTypes) {
			System.out.println(type);
		}
	}
}
