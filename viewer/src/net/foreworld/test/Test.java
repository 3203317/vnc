package net.foreworld.test;

import net.foreworld.vncviewer.NwDesk;
import net.foreworld.vncviewer.YunDesk;
import net.foreworld.vncviewer.rfb.LogWriter;

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
		try {
			_desk.start();
		} catch (Exception e) {
			vlog.error(e.getMessage());
		}
	}

}
