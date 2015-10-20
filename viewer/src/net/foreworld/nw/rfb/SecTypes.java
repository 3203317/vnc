package net.foreworld.nw.rfb;

/**
 *
 * @author huangxin (3203317@qq.com)
 *
 */
public class SecTypes {
	public final static int invalid = 0;
	public final static int none = 1;
	public final static int vncAuth = 2;

	public final static int RA2 = 5;
	public final static int RA2ne = 6;

	public final static int tight = 16;
	public final static int ultra = 17;
	public final static int TLS = 18;

	// result types

	public final static int resultOK = 0;
	public final static int resultFailed = 1;
	public final static int resultTooMany = 2; // deprecated

	public static String name(int num) {
		switch (num) {
		case none:
			return "None";
		case vncAuth:
			return "VncAuth";
		case RA2:
			return "RA2";
		case RA2ne:
			return "RA2ne";
		default:
			return "[unknown secType]";
		}
	}

	public static int num(String name) {
		if ("None".equalsIgnoreCase(name))
			return none;
		if ("VncAuth".equalsIgnoreCase(name))
			return vncAuth;
		if ("RA2".equalsIgnoreCase(name))
			return RA2;
		if ("RA2ne".equalsIgnoreCase(name))
			return RA2ne;
		return invalid;
	}
	// std::list<int> parseSecTypes(const char* types);
}
