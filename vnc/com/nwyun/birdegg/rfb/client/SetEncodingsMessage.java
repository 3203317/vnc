package com.nwyun.birdegg.rfb.client;

import java.util.Set;

import com.nwyun.birdegg.exception.TransportException;
import com.nwyun.birdegg.rfb.encoding.EncodingType;
import com.nwyun.birdegg.transport.Writer;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class SetEncodingsMessage implements ClientToServerMessage {
	private final Set<EncodingType> encodings;

	public SetEncodingsMessage(Set<EncodingType> set) {
		encodings = set;
	}

	@Override
	public void send(Writer writer) throws TransportException {
		writer.writeByte(SET_ENCODINGS);
		writer.writeByte(0); // padding byte
		writer.writeInt16(encodings.size());
		for (EncodingType enc : encodings) {
			writer.writeInt32(enc.getId());
		}
		writer.flush();
	}

	@Override
	public String toString() {
		StringBuilder _sb = new StringBuilder(
				"SetEncodingsMessage: [encodings: ");
		for (EncodingType enc : encodings) {
			_sb.append(enc.name()).append(',');
		}
		_sb.setLength(_sb.length() - 1);
		return _sb.append(']').toString();
	}
}
