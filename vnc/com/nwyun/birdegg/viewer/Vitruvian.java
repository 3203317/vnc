package com.nwyun.birdegg.viewer;

import java.awt.Dimension;

import javax.swing.JPanel;

import com.nwyun.birdegg.rfb.protocol.ProtocolContext;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
@SuppressWarnings("serial")
public class Vitruvian extends JPanel implements Canvas {

	private ProtocolContext ctx;
	private double scaleFactor;

	public Vitruvian(ProtocolContext ctx, int width, int height,
			double scaleFactor) {
		this.ctx = ctx;
		this.width = width;
		this.height = height;
		this.scaleFactor = scaleFactor;
		initSize();
	}

	private void initSize() {
		setSize(getPreferredSize());
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension((int) (width * scaleFactor),
				(int) (height * scaleFactor));
	}

	private int height;
	private int width;
	private String title;

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

}
