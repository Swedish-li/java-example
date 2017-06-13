package com.lrs.common.auth;

import static org.junit.Assert.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.Md5Crypt;
import org.junit.Test;

/**
 * 常见加密技术
 * 
 * @author Swedish-li
 *
 */
public class EncryptionTech {

	/**
	 * Base64算法： 基于64个可打印字符来表示二进制数据的表示方法，对消息进行编码
	 * 
	 * 字母A-Z、a-z、数字0-9，这样共有62个字符，此外两个可打印符号在不同的系统中而不同(+,/)
	 */
	@Test
	public void testBase64() {
		// 编码为Base64
		byte[] bytes = Base64.encodeBase64("作漫画的《PERFECT WORLD》宣布真人电影化决定".getBytes());

		String encodeStr = new String(bytes);
		// RFC上规定用=来填充结尾
		assertEquals("5L2c5ryr55S755qE44CKUEVSRkVDVCBXT1JMROOAi+Wuo+W4g+ecn+S6uueUteW9seWMluWGs+Wumg==", encodeStr);
		// 解码
		String decodeStr = new String(Base64.decodeBase64(encodeStr));
		assertEquals("作漫画的《PERFECT WORLD》宣布真人电影化决定", decodeStr);

	}

	// ---------------------Message digest(消息摘要) -------------------------
	/**
	 * MD5消息摘要算法（英语：MD5 Message-Digest Algorithm）
	 * 
	 * 
	 */
	@Test
	public void testMd5() {
		// 生成随机salt,在入参中不可使用这个方法获取
		// salt规则："^" + prefix.replace("$", "\\$") +
		// "([\\.\\/a-zA-Z0-9]{1,8}).*"
		// String salt = getRandomSalt(8);

		String salt = "$1$" + "892llsjabc";
		String str = "5L2c5ryr55S755qE44CK";
		// 使用随机盐进行加密
		String str1 = Md5Crypt.md5Crypt(str.getBytes());
		String str2 = Md5Crypt.md5Crypt(str.getBytes());
		// $1$66e0CJAV$icMkicpysElvleruQpw9d0
		assertTrue(!str1.equals(str2));

		String cryptStr1 = Md5Crypt.md5Crypt(str.getBytes(), salt);
		String cryptStr2 = Md5Crypt.md5Crypt(str.getBytes(), salt);

		assertEquals(cryptStr1, cryptStr2);
	}

	@Test
	public void testJdkMd5() throws NoSuchAlgorithmException {
		String str = "5L2c5ryr55S755qE44CK";

		MessageDigest md = MessageDigest.getInstance("md5");
		byte[] cryptBytes = md.digest(str.getBytes());
		StringBuilder cryptStrBuilder = new StringBuilder();
		for (byte b : cryptBytes) {
			cryptStrBuilder.append(Integer.toHexString((b & 0xFF) | 0x100));
		}
		
		
	}

	/**
	 * 安全散列算法（英语：Secure Hash Algorithm，缩写为SHA）
	 * 
	 */
	@Test
	public void testSha() {

	}

	static final String B64T = "./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	/**
	 * Generates a string of random chars from the B64T set.
	 *
	 * @param num
	 *            Number of chars to generate.
	 */
	static String getRandomSalt(final int num) {
		final StringBuilder saltString = new StringBuilder();
		for (int i = 1; i <= num; i++) {
			saltString.append(B64T.charAt(new Random().nextInt(B64T.length())));
		}
		return saltString.toString();
	}

}
