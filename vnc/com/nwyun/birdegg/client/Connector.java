package com.nwyun.birdegg.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import com.glavsoft.rfb.IPasswordRetriever;
import com.glavsoft.rfb.protocol.Protocol;
import com.glavsoft.rfb.protocol.ProtocolSettings;
import com.glavsoft.transport.Reader;
import com.glavsoft.transport.Writer;
import com.glavsoft.viewer.UiSettings;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class Connector {
	private RfbServer _server;

	private JFrame _frame;
	private JScrollPane _scroller;
	protected Socket _workingSocket;
	protected Protocol _workingProtocol;
	protected ProtocolSettings _rfbSettings;
	protected String _connectionString;
	protected UiSettings _uiSettings;

	public Connector(RfbServer server) throws Exception {
		_server = server;
		_workingSocket = new Socket(server.getIp(), server.getPort());
		_workingSocket.setTcpNoDelay(true); // disable Nagle algorithm
		Reader reader = new Reader(_workingSocket.getInputStream());
		Writer writer = new Writer(_workingSocket.getOutputStream());
		// TODO
		PasswordChooser pc = new PasswordChooser(_connectionString);
		_rfbSettings = ProtocolSettings.getDefaultSettings();
		_uiSettings = new UiSettings();
		// TODO
		_workingProtocol = new Protocol(reader, writer, pc, _rfbSettings);
		_workingProtocol.handshake();
	}

	public void connect() {
		System.out.println("connect");
		Surface surface = createSurface();
		createContainer();
	}

	private Surface createSurface() {
		Surface surface = new Surface(_workingProtocol,
				_uiSettings.getScaleFactor());
		return surface;
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

	private class PasswordChooser implements IPasswordRetriever {
		private String connectionString;

		private PasswordChooser(String connectionString) {
			this.connectionString = connectionString;
		}

		@Override
		public String getPassword() {
			return _server.getPassword();
		}
	}
}
