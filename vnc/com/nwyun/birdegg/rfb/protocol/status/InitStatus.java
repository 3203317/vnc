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
		ServerInitMessage serverInitMessage = getServerInitMessage();
		completeContextData(serverInitMessage);
	}

	protected void completeContextData(ServerInitMessage serverInitMessage) {
		ctx.setFbHeight(serverInitMessage.getFrameBufferHeight());
		ctx.setFbWidth(serverInitMessage.getFrameBufferWidth());
		ctx.setRemoteDesktopName(serverInitMessage.getName());
		ctx.setPixelFormat(serverInitMessage.getPixelFormat());
		_logger.info(serverInitMessage.toString());
	}

	private ServerInitMessage getServerInitMessage() {
		writer.write(ctx.getSettings().getSharedFlag());
		return new ServerInitMessage(reader);
	}
}
