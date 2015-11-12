package com.nwyun.birdegg.exception;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class ProtocolException extends RuntimeException {
	private static final long serialVersionUID = -2211177350674635855L;

	public ProtocolException() {
		super("协议处理异常");
	}
}
