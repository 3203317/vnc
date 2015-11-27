package com.nwyun.birdegg.rfb.encoding.decoder;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.nwyun.birdegg.rfb.encoding.EncodingType;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class DecodersContainer {
	private static Map<EncodingType, Class<? extends Decoder>> knownDecoders = new HashMap<EncodingType, Class<? extends Decoder>>();
	static {
		knownDecoders.put(EncodingType.ZLIB, ZlibDecoder.class);
		knownDecoders.put(EncodingType.ZRLE, ZRLEDecoder.class);
	}

	private final Map<EncodingType, Decoder> decoders = new HashMap<EncodingType, Decoder>();

	public DecodersContainer() {
		decoders.put(EncodingType.RAW_ENCODING, RawDecoder.getInstance());
		try {
			decoders.put(EncodingType.ZLIB, knownDecoders
					.get(EncodingType.ZLIB).newInstance());
		} catch (InstantiationException e) {
			logError(EncodingType.ZLIB, e);
		} catch (IllegalAccessException e) {
			logError(EncodingType.ZLIB, e);
		}
		try {
			decoders.put(EncodingType.ZRLE, knownDecoders
					.get(EncodingType.ZRLE).newInstance());
		} catch (InstantiationException e) {
			logError(EncodingType.ZRLE, e);
		} catch (IllegalAccessException e) {
			logError(EncodingType.ZRLE, e);
		}
	}

	private void logError(EncodingType enc, Exception e) {
		Logger.getLogger(this.getClass().getName()).severe(
				"Can not instantiate decoder for encoding type '"
						+ enc.getName() + "' " + e.getMessage());
	}

	public Decoder getDecoderByType(EncodingType type) {
		return decoders.get(type);
	}

	public void resetDecoders() {
		for (Decoder decoder : decoders.values()) {
			if (null != decoder) {
				decoder.reset();
			}
		}
	}
}
