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
	private final Logger logger;

	public InitStatus(ProtocolContext ctx) {
		super(ctx);
		logger = Logger.getLogger(getClass().getName());
	}

	@Override
	public void execute() {
		clientAndServerInit();
	}

	private void clientAndServerInit() {
		ServerInitMessage message = getServerInitMessage();
		fillServerData(message);
	}

	protected void fillServerData(ServerInitMessage _message) {
		ctx.setFrameBufferHeight(_message.getFrameBufferHeight());
		ctx.setFrameBufferWidth(_message.getFrameBufferWidth());
		ctx.setRemoteDesktopName(_message.getName());
		ctx.setPixelFormat(_message.getPixelFormat());
		logger.info(_message.toString());
	}

	private ServerInitMessage getServerInitMessage() {
		writer.write(ctx.getSettings().getSharedFlag());
		return new ServerInitMessage(reader);
	}
}
