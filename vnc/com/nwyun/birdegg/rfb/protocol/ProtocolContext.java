package com.nwyun.birdegg.rfb.protocol;

import com.nwyun.birdegg.transport.Reader;
import com.nwyun.birdegg.transport.Writer;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public interface ProtocolContext {

	Writer getWriter();

	Reader getReader();

	ProtocolSettings getSettings();
}
