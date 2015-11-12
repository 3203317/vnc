package com.nwyun.birdegg.transport;

import java.io.DataOutputStream;
import java.io.OutputStream;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class Writer {
	private final DataOutputStream _os;

	public Writer(OutputStream os) {
		_os = new DataOutputStream(os);
	}
}
