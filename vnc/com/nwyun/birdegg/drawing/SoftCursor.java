package com.nwyun.birdegg.drawing;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public abstract class SoftCursor {
	protected int hotX, hotY;
	private int x, y;
	public int width, height;
	public int rX, rY;
	public int oldRX, oldRY;
	public int oldWidth, oldHeight;
	private final Object lock = new Object();

	public SoftCursor(int hotX, int hotY, int width, int height) {
		this.hotX = hotX;
		this.hotY = hotY;
		oldWidth = this.width = width;
		oldHeight = this.height = height;
		oldRX = rX = 0;
		oldRY = rY = 0;
	}

	/**
	 * Update cursor position
	 * 
	 * @param newX
	 * @param newY
	 */
	public void updatePosition(int newX, int newY) {
		oldRX = rX;
		oldRY = rY;
		oldWidth = width;
		oldHeight = height;
		x = newX;
		y = newY;
		rX = x - hotX;
		rY = y - hotY;
	}

	/**
	 * Set new cursor dimensions and hot point position
	 * 
	 * @param hotX
	 * @param hotY
	 * @param width
	 * @param height
	 */
	public void setNewDimensions(int hotX, int hotY, int width, int height) {
		this.hotX = hotX;
		this.hotY = hotY;
		oldWidth = this.width;
		oldHeight = this.height;
		oldRX = rX;
		oldRY = rY;
		rX = x - hotX;
		rY = y - hotY;
		this.width = width;
		this.height = height;
	}

	public void createCursor(int[] cursorPixels, int hotX, int hotY, int width,
			int height) {
		createNewCursorImage(cursorPixels, hotX, hotY, width, height);
		setNewDimensions(hotX, hotY, width, height);
	}

	protected abstract void createNewCursorImage(int[] cursorPixels, int hotX,
			int hotY, int width, int height);

	public Object getLock() {
		return lock;
	}
}