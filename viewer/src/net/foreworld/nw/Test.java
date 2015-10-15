package net.foreworld.nw;

import net.foreworld.nw.listener.OnShowListener;
import net.foreworld.nw.rfb.LogWriter;

public class Test implements Runnable {

	static LogWriter vlog = new LogWriter("Test");
	private NwServer _server;

	public static void main(String[] args) {
		Test t = new Test();

		Thread thread = new Thread(t);
		thread.run();
	}

	@Override
	public void run() {
		_server = new NwServer("192.168.6.128", 5901, "123222");

		_server.setOnShowListener(new OnShowListener() {
			@Override
			public void onShow() {
				// TODO
			}
		});

		try {
			_server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
