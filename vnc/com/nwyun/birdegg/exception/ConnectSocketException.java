package com.nwyun.birdegg.exception;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
@SuppressWarnings("serial")
public class ConnectSocketException extends RuntimeException {

	public ConnectSocketException() {
		super("连接Socket异常");
	}
}
