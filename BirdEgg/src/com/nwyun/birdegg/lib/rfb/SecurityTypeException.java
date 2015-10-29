package com.nwyun.birdegg.lib.rfb;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class SecurityTypeException extends RuntimeException {
	private static final long serialVersionUID = -2938206369806113803L;

	public SecurityTypeException() {
		super("无效安全类型");
	}
}
