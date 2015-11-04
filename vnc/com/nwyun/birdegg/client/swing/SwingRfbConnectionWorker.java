package com.nwyun.birdegg.client.swing;

import java.net.Socket;

import javax.swing.SwingWorker;

import com.glavsoft.rfb.IRfbSessionListener;
import com.glavsoft.rfb.protocol.ProtocolSettings;
import com.glavsoft.viewer.RfbConnectionWorker;
import com.glavsoft.viewer.UiSettings;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class SwingRfbConnectionWorker extends SwingWorker<Void, String>
		implements RfbConnectionWorker, IRfbSessionListener {

	public SwingRfbConnectionWorker() {
		// TODO
		System.out.println("SwingRfbConnectionWorker");
	}

	@Override
	public Void doInBackground() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("doInBackground");
		return null;
	}

	@Override
	protected void done() {
		try {
			get();
			System.out.println("done");
		} catch (Exception e) {
			// TODO
		}
	}

	@Override
	public boolean cancel() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void rfbSessionStopped(String reason) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setWorkingSocket(Socket workingSocket) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setRfbSettings(ProtocolSettings rfbSettings) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setUiSettings(UiSettings uiSettings) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setConnectionString(String connectionString) {
		// TODO Auto-generated method stub

	}

}
