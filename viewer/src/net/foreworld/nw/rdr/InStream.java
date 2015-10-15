package net.foreworld.nw.rdr;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
public abstract class InStream {

	protected int ptr;
	protected int end;

	protected InStream() {
		// TODO
	}

	protected void readBytes(byte[] data, int offset, int len) {
		int offsetEnd = offset + len;
		while (offset < offsetEnd) {

		}
	}

}
