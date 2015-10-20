package net.foreworld.nw.exception;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
public class ServerVersionException extends RuntimeException {
	private static final long serialVersionUID = 359835055062185274L;

	public ServerVersionException() {
		super("服务器版本不可用");
	}
}
