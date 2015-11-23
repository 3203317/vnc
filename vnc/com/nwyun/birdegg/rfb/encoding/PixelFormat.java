package com.nwyun.birdegg.rfb.encoding;

import com.nwyun.birdegg.exception.TransportException;
import com.nwyun.birdegg.transport.Reader;
import com.nwyun.birdegg.transport.Writer;

/**
 * Pixel Format: 1 - U8 - bits-per-pixel 1 - U8 - depth 1 - U8 - big-endian-flag
 * 1 - U8 - true-color-flag 2 - U16 - red-max 2 - U16 - green-max 2 - U16 -
 * blue-max 1 - U8 - red-shift 1 - U8 - green-shift 1 - U8 - blue-shift 3 - -
 * padding
 */
public class PixelFormat {
	public byte bitsPerPixel;
	public byte depth;
	public byte bigEndianFlag;
	public byte trueColourFlag;
	public short redMax;
	public short greenMax;
	public short blueMax;
	public byte redShift;
	public byte greenShift;
	public byte blueShift;

	public void fill(Reader $reader) throws TransportException {
		bitsPerPixel = $reader.readByte();
		depth = $reader.readByte();
		bigEndianFlag = $reader.readByte();
		trueColourFlag = $reader.readByte();
		redMax = $reader.readInt16();
		greenMax = $reader.readInt16();
		blueMax = $reader.readInt16();
		redShift = $reader.readByte();
		greenShift = $reader.readByte();
		blueShift = $reader.readByte();
		$reader.readBytes(3); // skip padding bytes
	}

	public void send(Writer $writer) throws TransportException {
		$writer.write(bitsPerPixel);
		$writer.write(depth);
		$writer.write(bigEndianFlag);
		$writer.write(trueColourFlag);
		$writer.write(redMax);
		$writer.write(greenMax);
		$writer.write(blueMax);
		$writer.write(redShift);
		$writer.write(greenShift);
		$writer.write(blueShift);
		$writer.writeInt16(0); // padding bytes
		$writer.writeByte(0); // padding bytes
	}

	public static PixelFormat create24bitColorDepthPixelFormat(
			int $bigEndianFlag) {
		final PixelFormat _pixelFormat = new PixelFormat();
		_pixelFormat.bigEndianFlag = (byte) $bigEndianFlag;
		_pixelFormat.bitsPerPixel = 32;
		_pixelFormat.blueMax = 255;
		_pixelFormat.blueShift = 0;
		_pixelFormat.greenMax = 255;
		_pixelFormat.greenShift = 8;
		_pixelFormat.redMax = 255;
		_pixelFormat.redShift = 16;
		_pixelFormat.depth = 24;
		_pixelFormat.trueColourFlag = 1;
		return _pixelFormat;
	}

	/**
	 * specifies 65536 colors, 5bit per Red, 6bit per Green, 5bit per Blue
	 */
	public static PixelFormat create16bitColorDepthPixelFormat(
			int $bigEndianFlag) {
		final PixelFormat _pixelFormat = new PixelFormat();
		_pixelFormat.bigEndianFlag = (byte) $bigEndianFlag;
		_pixelFormat.bitsPerPixel = 16;
		_pixelFormat.blueMax = 31;
		_pixelFormat.blueShift = 0;
		_pixelFormat.greenMax = 63;
		_pixelFormat.greenShift = 5;
		_pixelFormat.redMax = 31;
		_pixelFormat.redShift = 11;
		_pixelFormat.depth = 16;
		_pixelFormat.trueColourFlag = 1;
		return _pixelFormat;
	}

	/**
	 * specifies 256 colors, 2bit per Blue, 3bit per Green & Red
	 */
	public static PixelFormat create8bitColorDepthBGRPixelFormat(
			int $bigEndianFlag) {
		final PixelFormat _pixelFormat = new PixelFormat();
		_pixelFormat.bigEndianFlag = (byte) $bigEndianFlag;
		_pixelFormat.bitsPerPixel = 8;
		_pixelFormat.redMax = 7;
		_pixelFormat.redShift = 0;
		_pixelFormat.greenMax = 7;
		_pixelFormat.greenShift = 3;
		_pixelFormat.blueMax = 3;
		_pixelFormat.blueShift = 6;
		_pixelFormat.depth = 8;
		_pixelFormat.trueColourFlag = 1;
		return _pixelFormat;
	}

	/**
	 * specifies 64 colors, 2bit per Red, Green & Blue
	 */
	public static PixelFormat create6bitColorDepthPixelFormat(int $bigEndianFlag) {
		final PixelFormat _pixelFormat = new PixelFormat();
		_pixelFormat.bigEndianFlag = (byte) $bigEndianFlag;
		_pixelFormat.bitsPerPixel = 8;
		_pixelFormat.blueMax = 3;
		_pixelFormat.blueShift = 0;
		_pixelFormat.greenMax = 3;
		_pixelFormat.greenShift = 2;
		_pixelFormat.redMax = 3;
		_pixelFormat.redShift = 4;
		_pixelFormat.depth = 6;
		_pixelFormat.trueColourFlag = 1;
		return _pixelFormat;
	}

	/**
	 * specifies 8 colors, 1bit per Red, Green & Blue
	 */
	public static PixelFormat create3bppPixelFormat(int $bigEndianFlag) {
		final PixelFormat _pixelFormat = new PixelFormat();
		_pixelFormat.bigEndianFlag = (byte) $bigEndianFlag;
		_pixelFormat.bitsPerPixel = 8;
		_pixelFormat.blueMax = 1;
		_pixelFormat.blueShift = 0;
		_pixelFormat.greenMax = 1;
		_pixelFormat.greenShift = 1;
		_pixelFormat.redMax = 1;
		_pixelFormat.redShift = 2;
		_pixelFormat.depth = 3;
		_pixelFormat.trueColourFlag = 1;
		return _pixelFormat;
	}

	@Override
	public String toString() {
		return "PixelFormat: [bits-per-pixel: "
				+ String.valueOf(0xff & bitsPerPixel) + ", depth: "
				+ String.valueOf(0xff & depth) + ", big-endian-flag: "
				+ String.valueOf(0xff & bigEndianFlag) + ", true-color-flag: "
				+ String.valueOf(0xff & trueColourFlag) + ", red-max: "
				+ String.valueOf(0xffff & redMax) + ", green-max: "
				+ String.valueOf(0xffff & greenMax) + ", blue-max: "
				+ String.valueOf(0xffff & blueMax) + ", red-shift: "
				+ String.valueOf(0xff & redShift) + ", green-shift: "
				+ String.valueOf(0xff & greenShift) + ", blue-shift: "
				+ String.valueOf(0xff & blueShift) + "]";
	}
}
