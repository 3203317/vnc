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
		byte[] challenge = reader.readBytes(16);
		String password = passwordNeed.getPassword();
		if (null == password)
			return;
		byte[] key = new byte[8];
		System.arraycopy(password.getBytes(), 0, key, 0,
				Math.min(key.length, password.getBytes().length));
		writer.write(encrypt(challenge, key));
	}

	/**
	 * Encript challenge by key using DES
	 * 
	 * @return encripted bytes
	 * @throws CryptoException
	 *             on problem with DES algorithm support or smth about
	 */
	public byte[] encrypt(byte[] challenge, byte[] key) {
		try {
			DESKeySpec desKeySpec = new DESKeySpec(mirrorBits(key));
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
			Cipher desCipher = Cipher.getInstance("DES/ECB/NoPadding");
			desCipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return desCipher.doFinal(challenge);
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

	private byte[] mirrorBits(byte[] k) {
		byte[] key = new byte[8];
		for (int i = 0; i < 8; i++) {
			byte s = k[i];
			s = (byte) (((s >> 1) & 0x55) | ((s << 1) & 0xaa));
			s = (byte) (((s >> 2) & 0x33) | ((s << 2) & 0xcc));
			s = (byte) (((s >> 4) & 0x0f) | ((s << 4) & 0xf0));
			key[i] = s;
		}
		return key;
	}
}
