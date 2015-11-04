package com.nwyun.birdegg.client;

import com.glavsoft.viewer.RfbConnectionWorker;
import com.nwyun.birdegg.client.swing.SwingRfbConnectionWorker;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class Connector {
	private RfbServer _server;

	public Connector(RfbServer server) {
		_server = server;
	}

	public void connect() {
		System.out.println(_server);
		RfbConnectionWorker work = new SwingRfbConnectionWorker();
		work.execute();
	}
}
