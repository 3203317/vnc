package com.nwyun.birdegg.util;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public abstract class Interceptor {
	protected Interceptor successor;

	protected void setSuccessor(Interceptor successor) {
		this.successor = successor;
	}

	public abstract void requestInterceptor();
}
