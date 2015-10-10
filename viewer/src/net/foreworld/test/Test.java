package net.foreworld.test;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Test {
	private String ip = "192.168.6.128";
	private int port = 5901;

	private Socket sock;

	public static void main(String[] args) {

		Test t = new Test();
		try {
			t.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void start() throws UnknownHostException, IOException {
		sock = new Socket(ip, port);
		System.out.println(sock.getInputStream());
	}
}
