package com.nwyun.birdegg.exception;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class ConnectSocketException extends RuntimeException {
	private static final long serialVersionUID = -4576256981436390320L;

	public ConnectSocketException() {
		super("连接Socket异常");
	}
}
