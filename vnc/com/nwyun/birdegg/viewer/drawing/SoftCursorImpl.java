package com.nwyun.birdegg.viewer.drawing;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;

import com.nwyun.birdegg.drawing.SoftCursor;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class SoftCursorImpl extends SoftCursor {
	private Image cursorImage;

	public SoftCursorImpl(int hotX, int hotY, int width, int height) {
		super(hotX, hotY, width, height);
	}

	public Image getImage() {
		return cursorImage;
	}

	@Override
	protected void createNewCursorImage(int[] cursorPixels, int hotX, int hotY,
			int width, int height) {
		cursorImage = Toolkit.getDefaultToolkit().createImage(
				new MemoryImageSource(width, height, cursorPixels, 0, width));
	}
}
