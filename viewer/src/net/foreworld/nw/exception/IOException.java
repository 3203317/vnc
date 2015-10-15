package net.foreworld.nw.exception;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
public class IOException extends Exception {

	private static final long serialVersionUID = 2726790053872712345L;
	java.io.IOException _ex;

	public IOException(java.io.IOException ex) {
		super(ex.toString());
		_ex = ex;
	}

}
