package com.nwyun.birdegg.rfb.protocol.status;

import java.util.logging.Logger;

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
	private final Logger logger;

	public SecurityTypeStatus(ProtocolContext ctx) {
		super(ctx);
		logger = Logger.getLogger(getClass().getName());
	}

	@Override
	public void execute() {
		int _secTypesNum = reader.readUInt8();
		if (0 == _secTypesNum) {
			throw new UnsupportedSecurityTypeException(reader.readString());
		}

		byte[] _secTypes = reader.readBytes(_secTypesNum);
		logger.info("Security types received (" + _secTypesNum + "): "
				+ Strings.toString(_secTypes));

		AuthHandler _typeSelected = selectAuthHandler(_secTypes,
				ctx.getSettings().authCapabilities);

		setUseSecurityResult(_typeSelected);
		// TODO
		writer.writeByte(_typeSelected.getId());
		logger.info("Security Type accepted: " + _typeSelected.getName());
		changeStatusTo(new AuthenticationStatus(ctx, _typeSelected));
	}

	private AuthHandler selectAuthHandler(byte[] $secTypes,
			CapabilityContainer $authCapabilities) {
		AuthHandler _typeSelected = null;
		for (byte _type : $secTypes) {
			// TODO
		}

		for (byte _type : $secTypes) {
			_typeSelected = SecurityType.implementedSecurityTypes
					.get(0xff & _type);
			if (null != _typeSelected && $authCapabilities.isSupported(0))
				return _typeSelected;
		}

		throw new UnsupportedSecurityTypeException(
				"No security types supported. Server sent '"
						+ Strings.toString($secTypes)
						+ "' security types, but we do not support any of their.");
	}

	protected void setUseSecurityResult(AuthHandler typeSelected) {
		// nop for Protocol version 3.8 and above
	}
}
