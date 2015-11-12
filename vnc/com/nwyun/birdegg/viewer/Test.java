package com.nwyun.birdegg.viewer;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JApplet;
import javax.swing.SwingUtilities;

import com.nwyun.birdegg.client.Connector;
import com.nwyun.birdegg.server.NwServer;
import com.nwyun.birdegg.server.Server;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class Test extends JApplet implements Runnable, WindowListener {
	private static final long serialVersionUID = 5445528667317605631L;

	public static void main(String[] args) {
		Test t = new Test();
		SwingUtilities.invokeLater(t);
	}

	public Test() {
		// TODO
	}

	@Override
	public void run() {
		Server server = new NwServer("192.168.6.128", 5901);
		Connector connector = new Connector(server);
		connector.connect();
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}
}
