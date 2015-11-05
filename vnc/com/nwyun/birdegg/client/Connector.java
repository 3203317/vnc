package com.nwyun.birdegg.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class Connector {
	private RfbServer _server;

	private JFrame _frame;
	private JScrollPane _scroller;

	public Connector(RfbServer server) {
		_server = server;
	}

	public void connect() {
		System.out.println("connect");
		createContainer();
	}

	private void createContainer() {
		Dimension dim = new Dimension();
		dim.width = 1024;
		dim.height = 768;

		_scroller = new JScrollPane();

		_frame = new JFrame();
		_frame.setSize(dim);
		_frame.setLayout(new BorderLayout(0, 0));

		_frame.add(_scroller, BorderLayout.CENTER);

		_frame.setVisible(true);
		_frame.validate();
		_frame.setTitle("vnc");

		_frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				int ret = JOptionPane.showConfirmDialog(null, "确定关闭吗？",
						"Attention", JOptionPane.OK_OPTION);
				if (ret == JOptionPane.OK_OPTION)
					System.exit(0);
			}
		});
	}
}
