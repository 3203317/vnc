package com.nwyun.birdegg.exception;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
@SuppressWarnings("serial")
public class CryptoException extends RuntimeException {

	public CryptoException(String message, Throwable e) {
		super(message, e);
	}
}
