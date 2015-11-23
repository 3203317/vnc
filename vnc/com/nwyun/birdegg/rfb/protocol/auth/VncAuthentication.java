package com.nwyun.birdegg.rfb.protocol.auth;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import com.nwyun.birdegg.exception.CryptoException;
import com.nwyun.birdegg.rfb.CapabilityContainer;
import com.nwyun.birdegg.rfb.IPasswordNeed;
import com.nwyun.birdegg.transport.Reader;
import com.nwyun.birdegg.transport.Writer;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class VncAuthentication extends AuthHandler {

	@Override
	public SecurityType getType() {
		return SecurityType.VNC_AUTHENTICATION;
	}

	@Override
	public void authenticate(Reader reader, Writer writer,
			CapabilityContainer authCaps, IPasswordNeed passwordNeed) {
		String _password = passwordNeed.getPassword();
		if (null == _password)
			_password = "";
		byte[] _key = new byte[8];
		System.arraycopy(_password.getBytes(), 0, _key, 0,
				Math.min(_key.length, _password.getBytes().length));
		byte[] _challenge = reader.readBytes(16);
		writer.write(encrypt(_challenge, _key));
	}

	/**
	 * Encript challenge by key using DES
	 * 
	 * @return encripted bytes
	 * @throws CryptoException
	 *             on problem with DES algorithm support or smth about
	 */
	public byte[] encrypt(byte[] $challenge, byte[] $key) {
		try {
			DESKeySpec _desKeySpec = new DESKeySpec(mirrorBits($key));
			SecretKeyFactory _keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey _secretKey = _keyFactory.generateSecret(_desKeySpec);
			Cipher _desCipher = Cipher.getInstance("DES/ECB/NoPadding");
			_desCipher.init(Cipher.ENCRYPT_MODE, _secretKey);
			return _desCipher.doFinal($challenge);
		} catch (NoSuchAlgorithmException e) {
			throw new CryptoException("Cannot encrypt challenge", e);
		} catch (NoSuchPaddingException e) {
			throw new CryptoException("Cannot encrypt challenge", e);
		} catch (IllegalBlockSizeException e) {
			throw new CryptoException("Cannot encrypt challenge", e);
		} catch (BadPaddingException e) {
			throw new CryptoException("Cannot encrypt challenge", e);
		} catch (InvalidKeyException e) {
			throw new CryptoException("Cannot encrypt challenge", e);
		} catch (InvalidKeySpecException e) {
			throw new CryptoException("Cannot encrypt challenge", e);
		}
	}

	private byte[] mirrorBits(byte[] $k) {
		byte[] _key = new byte[8];
		for (int i = 0; i < 8; i++) {
			byte _s = $k[i];
			_s = (byte) (((_s >> 1) & 0x55) | ((_s << 1) & 0xaa));
			_s = (byte) (((_s >> 2) & 0x33) | ((_s << 2) & 0xcc));
			_s = (byte) (((_s >> 4) & 0x0f) | ((_s << 4) & 0xf0));
			_key[i] = _s;
		}
		return _key;
	}
}
