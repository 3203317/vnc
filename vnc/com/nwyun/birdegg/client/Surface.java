package com.nwyun.birdegg.client;

import java.awt.Dimension;

import javax.swing.JPanel;

import com.glavsoft.core.SettingsChangedEvent;
import com.glavsoft.drawing.Renderer;
import com.glavsoft.rfb.IChangeSettingsListener;
import com.glavsoft.rfb.IRepaintController;
import com.glavsoft.rfb.encoding.PixelFormat;
import com.glavsoft.rfb.encoding.decoder.FramebufferUpdateRectangle;
import com.glavsoft.rfb.protocol.ProtocolContext;
import com.glavsoft.transport.Reader;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class Surface extends JPanel implements IRepaintController,
		IChangeSettingsListener {

	private int _width;
	private int _height;
	private double _scaleFactor;
	private final ProtocolContext _context;

	public Surface(ProtocolContext context, double scaleFactor) {
		_context = context;
		_scaleFactor = scaleFactor;
		init(context.getFbWidth(), context.getFbHeight());
	}

	private void init(int width, int height) {
		_width = width;
		_height = height;
		setSize(getPreferredSize());
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension((int) (_width * _scaleFactor),
				(int) (_height * _scaleFactor));
	}

	@Override
	public void settingsChanged(SettingsChangedEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void repaintBitmap(FramebufferUpdateRectangle rect) {
		repaintBitmap(rect.x, rect.y, rect.width, rect.height);
	}

	@Override
	public void repaintBitmap(int x, int y, int width, int height) {
		repaint((int) (x * _scaleFactor), (int) (y * _scaleFactor),
				(int) Math.ceil(width * _scaleFactor),
				(int) Math.ceil(height * _scaleFactor));
	}

	@Override
	public void repaintCursor() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCursorPosition(short x, short y) {
		// TODO Auto-generated method stub

	}

	@Override
	public Renderer createRenderer(Reader reader, int width, int height,
			PixelFormat pixelFormat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPixelFormat(PixelFormat pixelFormat) {
		// TODO Auto-generated method stub

	}
}
