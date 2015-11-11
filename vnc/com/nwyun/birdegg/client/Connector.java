package com.nwyun.birdegg.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

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

		JPanel outerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		outerPanel.setBackground(Color.DARK_GRAY);

		_scroller = new JScrollPane(outerPanel);

		_frame = new JFrame();
		_frame.setSize(dim);
		_frame.setLayout(new BorderLayout(0, 0));

		_frame.add(_scroller, BorderLayout.CENTER);

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
}
