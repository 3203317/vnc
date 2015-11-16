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

	int getFbWidth();

	void setFbWidth(int frameBufferWidth);

	int getFbHeight();

	void setFbHeight(int frameBufferHeight);

	String getRemoteDesktopName();

	void setRemoteDesktopName(String name);

	PixelFormat getPixelFormat();

	void setPixelFormat(PixelFormat pixelFormat);
}
