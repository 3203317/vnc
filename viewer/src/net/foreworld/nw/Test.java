package net.foreworld.nw;

import net.foreworld.nw.rfb.Connector;
import net.foreworld.nw.rfb.LogWriter;
import net.foreworld.nw.rfb.RfbServer;

public class Test implements Runnable {

	static LogWriter vlog = new LogWriter("Test");
	private RfbServer _server;

	public static void main(String[] args) {
		Test t = new Test();

		Thread thread = new Thread(t);
		thread.run();
	}

	@Override
	public void run() {
		_server = new NwServer("192.168.6.128", 5901, "123222");

		Connector connector = new Connector(_server);

		connector.connect();
	}

}
