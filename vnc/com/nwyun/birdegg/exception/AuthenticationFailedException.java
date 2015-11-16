package com.nwyun.birdegg.exception;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
@SuppressWarnings("serial")
public class AuthenticationFailedException extends RuntimeException {

	public AuthenticationFailedException(String message) {
		super(message);
	}
}
