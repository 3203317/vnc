package net.foreworld.vncviewer;

import java.awt.Color;
import java.awt.Image;

import net.foreworld.vncviewer.rfb.BoolParameter;

public class VNCViewer extends java.applet.Applet implements Runnable {

	private static final long serialVersionUID = 1822348366928312522L;

	Thread thread;
	Image logo;
	boolean applet, firstApplet;

	BoolParameter alwaysShowServerDialog = new BoolParameter(
			"AlwaysShowServerDialog",
			"Always show the server dialog even if a server "
					+ "has been specified in an applet parameter or on "
					+ "the command line", false);

	public static void main(String[] argv) {
		VNCViewer viewer = new VNCViewer(argv);
		viewer.start();
	}

	public VNCViewer() {
		applet = true;
		firstApplet = true;
	}

	public VNCViewer(String[] argv) {

	}

	public void init() {
		setBackground(Color.red);
		logo = getImage(getDocumentBase(), "logo150x150.gif");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	public void start() {
		if (firstApplet) {
			alwaysShowServerDialog.setParam(true);
		}

		thread = new Thread(this);
		thread.start();
	}

}
