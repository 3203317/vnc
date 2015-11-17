package com.nwyun.birdegg.viewer;

import com.nwyun.birdegg.server.Server;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public interface Window {
	void setHeight(int height);

	int getHeight();

	void setWidth(int width);

	int getWidth();

	void setServer(Server server);

	void open(String title);
}
