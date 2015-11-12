package com.nwyun.birdegg.exception;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
@SuppressWarnings("serial")
public class ClosedConnectionException extends TransportException {

	public ClosedConnectionException(Throwable e) {
		super(e);
	}
}
