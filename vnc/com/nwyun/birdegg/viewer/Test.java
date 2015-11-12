package com.nwyun.birdegg.viewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import com.nwyun.birdegg.client.Connector;
import com.nwyun.birdegg.server.NwServer;
import com.nwyun.birdegg.server.Server;
import com.nwyun.birdegg.util.WorkHandler;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class Test extends JApplet implements Runnable, WindowListener {
	private static final long serialVersionUID = 5445528667317605631L;

	private JFrame _frame;

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
		connector.connect(new WorkHandler() {
			@Override
			public void success() {
				createContainer();
			}

			@Override
			public void failure() {
				// TODO
			}
		});
	}

	private void createContainer() {
		Dimension dim = new Dimension();
		dim.width = 1024;
		dim.height = 768;

		JPanel outerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		outerPanel.setBackground(Color.DARK_GRAY);

		_frame = new JFrame();
		_frame.setSize(dim);
		_frame.setLayout(new BorderLayout(0, 0));

		_frame.setVisible(true);
		_frame.validate();
		_frame.setTitle("vnc");

		_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		_frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				int ret = JOptionPane.showConfirmDialog(null, "确定关闭吗？", "关闭",
						JOptionPane.OK_OPTION);
				if (ret == JOptionPane.OK_OPTION)
					System.exit(0);
			}
		});
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
