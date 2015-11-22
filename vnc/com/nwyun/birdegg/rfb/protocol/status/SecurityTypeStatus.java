package com.nwyun.birdegg.rfb.protocol.status;

import java.util.logging.Logger;

import com.nwyun.birdegg.exception.SecurityTypeStatusException;
import com.nwyun.birdegg.exception.UnsupportedSecurityTypeException;
import com.nwyun.birdegg.rfb.CapabilityContainer;
import com.nwyun.birdegg.rfb.protocol.ProtocolContext;
import com.nwyun.birdegg.rfb.protocol.auth.AuthHandler;
import com.nwyun.birdegg.rfb.protocol.auth.SecurityType;
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
	public void execute() {
		int secTypesNum = reader.readUInt8();
		if (0 == secTypesNum) {
			throw new SecurityTypeStatusException(reader.readString());
		}

		byte[] secTypes = reader.readBytes(secTypesNum);
		_logger.info("Security types received (" + secTypesNum + "): "
				+ Strings.toString(secTypes));

		AuthHandler typeSelected = selectAuthHandler(secTypes,
				ctx.getSettings().authCapabilities);

		setUseSecurityResult(typeSelected);
		// TODO
		writer.writeByte(typeSelected.getId());
		_logger.info("Security Type accepted: " + typeSelected.getName());
		changeStatusTo(new AuthenticationStatus(ctx, typeSelected));
	}

	private AuthHandler selectAuthHandler(byte[] secTypes,
			CapabilityContainer authCapabilities) {
		AuthHandler typeSelected = null;
		for (byte type : secTypes) {
			// TODO
		}

		for (byte type : secTypes) {
			typeSelected = SecurityType.implementedSecurityTypes
					.get(0xff & type);
			if (null != typeSelected && authCapabilities.isSupported(0))
				return typeSelected;
		}

		throw new UnsupportedSecurityTypeException(
				"No security types supported. Server sent '"
						+ Strings.toString(secTypes)
						+ "' security types, but we do not support any of their.");
	}

	protected void setUseSecurityResult(AuthHandler typeSelected) {
		// nop for Protocol version 3.8 and above
	}
}
