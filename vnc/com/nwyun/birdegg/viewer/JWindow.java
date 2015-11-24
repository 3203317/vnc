package com.nwyun.birdegg.viewer;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
@SuppressWarnings("serial")
public class JWindow extends JFrame implements WindowListener {
	private final Logger logger;

	public JWindow() {
		logger = Logger.getLogger(getClass().getName());
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
