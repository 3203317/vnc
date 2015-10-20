package net.foreworld.nw.exception;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
public class ProcessMsgDefaultException extends RuntimeException {

	private static final long serialVersionUID = -584977373509085359L;

	public ProcessMsgDefaultException() {
		super("无效的消息状态");
	}
}
