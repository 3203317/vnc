package net.foreworld.nw.rdr;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
abstract public class InStream {
	protected byte[] b;
	protected int _ptr;
	protected int _end;

	public void readBytes(byte[] data, int offset, int len) {
		int offsetEnd = offset + len;
		while (offset < offsetEnd) {
			// TODO
		}
	}

	abstract public void close();

	public final int check(int itemSize, int byteLen) {
		if (_ptr + itemSize * byteLen > _end) {
			if (_ptr + itemSize > _end) {

			}
			byteLen = (_end - _ptr) / itemSize;
		}
		return byteLen;
	}

	abstract protected int overRun(int itemSize, int byteLen);
}
