package com.nwyun.birdegg.viewer;

import com.nwyun.birdegg.client.Connector;
import com.nwyun.birdegg.server.NwServer;
import com.nwyun.birdegg.server.Server;
import com.nwyun.birdegg.util.DoWorkHandler;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class Test implements Runnable {
	private Connector _connector;

	public static void main(String[] args) {
		Test t = new Test();
		t.run();
	}

	public Test() {
		// TODO
	}

	@Override
	public void run() {
		Server server = new NwServer("192.168.6.128", 5901, "123222");
		_connector = new Connector(server);
		_connector.test(new DoWorkHandler() {
			@Override
			public void success() {
				connect();
			}
		});
	}

	private void connect() {
		_connector.connect(new DoWorkHandler() {
			@Override
			public void success() {
				// TODO
			}
		});
	}
}
