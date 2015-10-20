package net.foreworld.nw.exception;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
public class CloseSocketException extends RuntimeException {
	private static final long serialVersionUID = 3095067392947636493L;

	public CloseSocketException() {
		super("关闭Socket异常");
	}
}
