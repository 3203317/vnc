package net.foreworld.nw.exception;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
public class ConnectServerException extends RuntimeException {
	private static final long serialVersionUID = 2521984545572889374L;

	public ConnectServerException() {
		super("连接鸟窝云桌面失败");
	}
}
