package com.nwyun.birdegg.rfb.protocol;

import com.nwyun.birdegg.util.Interceptor;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class VersionInterceptor extends Interceptor {

	@Override
	public void requestInterceptor() {
		successor.requestInterceptor();
	}
}
