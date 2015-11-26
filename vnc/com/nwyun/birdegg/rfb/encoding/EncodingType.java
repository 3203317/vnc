package com.nwyun.birdegg.rfb.encoding;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public enum EncodingType {
	RAW_ENCODING(0, "Raw"),
	/**
	 * 
	 */
	COPY_RECT(1, "CopyRect"),
	/**
	 * 
	 */
	RRE(2, "RRE"),
	/**
	 * 
	 */
	HEXTILE(5, "Hextile"),
	/**
	 * 
	 */
	ZRLE(16, "ZRLE"),
	/**
	 * 
	 */
	RICH_CURSOR(0xFFFFFF11, "RichCursor"),
	/**
	 * 
	 */
	DESKTOP_SIZE(0xFFFFFF21, "DesctopSize");

	private int id;
	private final String name;

	private EncodingType(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public static EncodingType byId(int id) {
		for (EncodingType type : values()) {
			if (type.getId() == id)
				return type;
		}
		throw new IllegalArgumentException("Unsupported encoding id: " + id);
	}
}
