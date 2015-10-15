package net.foreworld.nw;

import net.foreworld.nw.rfb.LogWriter;

public class Test implements Runnable {

	static LogWriter vlog = new LogWriter("Test");
	YunDesk _desk;

	public static void main(String[] args) {
		Test t = new Test();

		Thread thread = new Thread(t);
		thread.run();
	}

	@Override
	public void run() {
		_desk = new NwDesk("192.168.6.128", 5901, "123222");

		_desk.onShowListener(new OnShowListener() {
			@Override
			public void show() {
				System.out.println("show()");
			}
		});

		try {
			_desk.start();
		} catch (Exception e) {
			vlog.error(e.getMessage());
		}
	}

}
