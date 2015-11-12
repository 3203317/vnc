package com.nwyun.birdegg.rfb.protocol;

import com.nwyun.birdegg.rfb.protocol.status.ProtocolStatus;
import com.nwyun.birdegg.transport.Reader;
import com.nwyun.birdegg.transport.Writer;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public interface ProtocolContext {

	void changeStateTo(ProtocolStatus status);

	Writer getWriter();

	Reader getReader();
}
