package com.nwyun.birdegg.exception;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
@SuppressWarnings("serial")
public class TransportException extends RuntimeException {

	public TransportException(String message, Throwable e) {
		super(message, e);
	}

	public TransportException(Throwable e) {
		super(e);
	}
}
