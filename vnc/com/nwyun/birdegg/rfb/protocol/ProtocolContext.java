package com.nwyun.birdegg.rfb.protocol;

import com.nwyun.birdegg.rfb.IPasswordNeed;
import com.nwyun.birdegg.rfb.encoding.PixelFormat;
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

	IPasswordNeed getPasswordNeed();

	void setFrameBufferHeight(int frameBufferHeight);

	int getFrameBufferHeight();

	void setFrameBufferWidth(int frameBufferWidth);

	int getFrameBufferWidth();

	void setRemoteDesktopName(String remoteDesktopName);

	String getRemoteDesktopName();

	void setPixelFormat(PixelFormat pixelFormat);

	PixelFormat getPixelFormat();

}
