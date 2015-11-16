package com.nwyun.birdegg.rfb.protocol.status;

import java.util.logging.Logger;

import com.nwyun.birdegg.rfb.encoding.ServerInitMessage;
import com.nwyun.birdegg.rfb.protocol.ProtocolContext;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class InitStatus extends ProtocolStatus {
	private final Logger _logger;

	public InitStatus(ProtocolContext ctx) {
		super(ctx);
		_logger = Logger.getLogger(getClass().getName());
	}

	@Override
	public void next() {
		clientAndServerInit();
	}

	private void clientAndServerInit() {
		ServerInitMessage message = getServerInitMessage();
		fillServerData(message);
	}

	protected void fillServerData(ServerInitMessage message) {
		server.setHeight(message.getFrameBufferHeight());
		server.setWidth(message.getFrameBufferWidth());
		server.setName(message.getName());
		server.setPixelFormat(message.getPixelFormat());
		_logger.info(message.toString());
	}

	private ServerInitMessage getServerInitMessage() {
		writer.write(ctx.getSettings().getSharedFlag());
		return new ServerInitMessage(reader);
	}
}
