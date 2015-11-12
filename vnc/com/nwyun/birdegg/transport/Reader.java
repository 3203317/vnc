package com.nwyun.birdegg.transport;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.InputStream;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class Reader {

	private final DataInputStream _is;

	public Reader(InputStream is) {
		_is = new DataInputStream(new BufferedInputStream(is));
	}

	public String readString(int len) {
		return new String("");
	}
}
