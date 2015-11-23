package com.nwyun.birdegg.rfb.protocol.status;

import java.util.logging.Logger;

import com.nwyun.birdegg.rfb.encoding.ServerInitMessage;
import com.nwyun.birdegg.rfb.protocol.ProtocolContext;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class InitServerStatus extends ProtocolStatus {
	private final Logger logger;

	public InitServerStatus(ProtocolContext ctx) {
		super(ctx);
		logger = Logger.getLogger(getClass().getName());
	}

	@Override
	public void execute() {
		clientAndServerInit();
	}

	private void clientAndServerInit() {
		ServerInitMessage _message = getServerInitMessage();
		fillServerData(_message);
	}

	protected void fillServerData(ServerInitMessage $message) {
		ctx.setFrameBufferHeight($message.getFrameBufferHeight());
		ctx.setFrameBufferWidth($message.getFrameBufferWidth());
		ctx.setRemoteDesktopName($message.getName());
		ctx.setPixelFormat($message.getPixelFormat());
		logger.info($message.toString());
	}

	private ServerInitMessage getServerInitMessage() {
		writer.write(ctx.getSettings().getSharedFlag());
		return new ServerInitMessage(reader);
	}
}
