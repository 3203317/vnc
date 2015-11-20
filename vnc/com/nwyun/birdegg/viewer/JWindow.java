package com.nwyun.birdegg.viewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.nwyun.birdegg.server.Server;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
@SuppressWarnings("serial")
public class JWindow extends JFrame implements WindowListener, Window {
	private final Logger _logger;
	private Server _server;
	private Dimension _dimension;

	public JWindow() {
		_logger = Logger.getLogger(getClass().getName());
		_dimension = new Dimension();
		bindEvent();
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	}

	private void bindEvent() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				int ret = JOptionPane.showConfirmDialog(null, "确定要关闭吗？", "关闭",
						JOptionPane.YES_NO_OPTION);
				if (JOptionPane.YES_OPTION == ret)
					System.exit(0);
			}
		});
	}

	@Override
	public void open(String title) {
		JPanel outerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		outerPanel.setBackground(Color.DARK_GRAY);

		setSize(_dimension);
		setLayout(new BorderLayout(0, 0));

		setVisible(true);
		validate();
		setTitle(title);
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

	@Override
	public void setServer(Server server) {
		_server = server;
	}

	@Override
	public void setHeight(int height) {
		_dimension.height = height;
	}

	@Override
	public void setWidth(int width) {
		_dimension.width = width;
	}
}
