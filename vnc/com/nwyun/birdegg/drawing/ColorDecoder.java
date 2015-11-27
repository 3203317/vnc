package com.nwyun.birdegg.drawing;

import com.nwyun.birdegg.exception.TransportException;
import com.nwyun.birdegg.rfb.encoding.PixelFormat;
import com.nwyun.birdegg.transport.Reader;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class ColorDecoder {
	protected byte redShift;
	protected byte greenShift;
	protected byte blueShift;
	public short redMax;
	public short greenMax;
	public short blueMax;
	public final int bytesPerPixel;
	public final int bytesPerCPixel;
	private final byte[] buff;

	private int startShift;
	private int startShiftCompact;
	private int addShiftItem;

	public ColorDecoder(PixelFormat pf) {
		redShift = pf.redShift;
		greenShift = pf.greenShift;
		blueShift = pf.blueShift;
		redMax = pf.redMax;
		greenMax = pf.greenMax;
		blueMax = pf.blueMax;
		bytesPerPixel = pf.bitsPerPixel / 8;
		final long significant = redMax << redShift | greenMax << greenShift
				| blueMax << blueShift;
		bytesPerCPixel = pf.depth <= 24 // as in RFB
				&& 32 == pf.bitsPerPixel
				&& ((significant & 0x00ff000000L) == 0 || (significant & 0x000000ffL) == 0) ? 3
				: bytesPerPixel;
		buff = new byte[bytesPerPixel];
		if (0 == pf.bigEndianFlag) {
			startShift = 0;
			startShiftCompact = 0;
			addShiftItem = 8;
		} else {
			startShift = pf.bitsPerPixel - 8;
			startShiftCompact = Math.max(0, pf.depth - 8);
			addShiftItem = -8;
		}
	}

	protected int readColor(Reader reader) throws TransportException {
		return getColor(reader.readBytes(buff, 0, bytesPerPixel), 0);
	}

	protected int readCompactColor(Reader reader) throws TransportException {
		return getCompactColor(reader.readBytes(buff, 0, bytesPerCPixel), 0);
	}

	protected int convertColor(int rawColor) {
		return 255 * (rawColor >> redShift & redMax) / redMax << 16
				| 255 * (rawColor >> greenShift & greenMax) / greenMax << 8
				| 255 * (rawColor >> blueShift & blueMax) / blueMax;
	}

	public void fillRawComponents(byte[] comp, byte[] bytes, int offset) {
		int rawColor = getRawColor(bytes, offset);
		comp[0] = (byte) (rawColor >> redShift & redMax);
		comp[1] = (byte) (rawColor >> greenShift & greenMax);
		comp[2] = (byte) (rawColor >> blueShift & blueMax);
	}

	protected int getColor(byte[] bytes, int offset) {
		return convertColor(getRawColor(bytes, offset));
	}

	private int getRawColor(byte[] bytes, int offset) {
		int shift = startShift;
		int item = addShiftItem;
		int rawColor = (bytes[offset++] & 0xff) << shift;
		for (int i = 1; i < bytesPerPixel; ++i) {
			rawColor |= (bytes[offset++] & 0xff) << (shift += item);
		}
		return rawColor;
	}

	protected int getCompactColor(byte[] bytes, int offset) {
		int shift = startShiftCompact;
		int item = addShiftItem;
		int rawColor = (bytes[offset++] & 0xff) << shift;
		for (int i = 1; i < bytesPerCPixel; ++i) {
			rawColor |= (bytes[offset++] & 0xff) << (shift += item);
		}
		return convertColor(rawColor);
	}
}