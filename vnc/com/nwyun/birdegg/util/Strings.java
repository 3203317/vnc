package com.nwyun.birdegg.util;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class Strings {
	public static String toString(byte[] byteArray) {
		StringBuilder sb = new StringBuilder("[");
		boolean notFirst = false;
		for (byte b : byteArray) {
			if (notFirst) {
				sb.append(", ");
			} else {
				notFirst = true;
			}
			sb.append(b);
		}
		return sb.append("]").toString();
	}

	public static boolean isTrimmedEmpty(String s) {
		return null == s || (s.trim().length() == 0);
	}
}
