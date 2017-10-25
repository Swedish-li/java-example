package com.lrs.common.auth;

import static org.junit.Assert.*;

import static org.hamcrest.Matchers.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.Crypt;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;
import org.dom4j.util.SingletonStrategy;
import org.json.JSONObject;
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
	
	
	/**
	 * 安全散列算法（英语：Secure Hash Algorithm，缩写为SHA）
	 * 
	 */

	private String signStr = "{\"nickName\":\"Band\","
			+ "\"gender\":1,\"language\":\"zh_CN\","
			+ "\"city\":\"Guangzhou\",\"province\":\"Guangdong\","
			+ "\"country\":\"CN\","
			+ "\"avatarUrl\":\"http://wx.qlogo.cn/mmopen/vi_32/1vZvI39NWFQ9XM4LtQpFrQJ1xlgZxx3w7bQxKARol6503Iuswjjn6nIGBiaycAjAtpujxyzYsrztuuICqIM5ibXQ/0\"}"
			+ "HyVFkGl5F5OQWJZZaNzBBg==";
	
	/**
	 * 微信小程序 数据签名校验
	 */
	@Test
	public void testSha1() {
		String sha1Rs = DigestUtils.sha1Hex(signStr);

		assertThat("75e81ceda165f4ffa64f4068af58c64b8f54b88c", equalTo(sha1Rs));
	}
	

	static String AEC_CBC_128 = "AES/CBC/PKCS5Padding";

	String appId = "wx4f4bc4dec97d474b";
	String sessionKey = "tiihtNczf5v6AKRyjwEUhQ==";
	String encryptedData = "CiyLU1Aw2KjvrjMdj8YKliAjtP4gsMZM" +
			"QmRzooG2xrDcvSnxIMXFufNstNGTyaGS" +
			"9uT5geRa0W4oTOb1WT7fJlAC+oNPdbB+" +
			"3hVbJSRgv+4lGOETKUQz6OYStslQ142d" +
			"NCuabNPGBzlooOmB231qMM85d2/fV6Ch" +
			"evvXvQP8Hkue1poOFtnEtpyxVLW1zAo6" +
			"/1Xx1COxFvrc2d7UL/lmHInNlxuacJXw" +
			"u0fjpXfz/YqYzBIBzD6WUfTIF9GRHpOn" +
			"/Hz7saL8xz+W//FRAUid1OksQaQx4CMs" +
			"8LOddcQhULW4ucetDf96JcR3g0gfRK4P" +
			"C7E/r7Z6xNrXd2UIeorGj5Ef7b1pJAYB" +
			"6Y5anaHqZ9J6nKEBvB4DnNLIVWSgARns" +
			"/8wR2SiRS7MNACwTyrGvt9ts8p12PKFd" +
			"lqYTopNHR1Vf7XjfhQlVsAJdNiKdYmYV" +
			"oKlaRv85IfVunYzO0IKXsyl7JCUjCpoG" +
			"20f0a04COwfneQAGGwd5oa+T8yO5hzuy" +
			"Db/XcxxmK01EpqOyuxINew==";
	String iv = "r7BXXKkLb8qrSNn05n0qiA==";

	String expectedData = "{\"openId\":\"oGZUI0egBJY1zhBYw2KhdUfwVJJE\","
			+ "\"nickName\":\"Band\",\"gender\":1,\"language\":\"zh_CN\","
			+ "\"city\":\"Guangzhou\",\"province\":\"Guangdong\",\"country\":\"CN\","
			+ "\"avatarUrl\":\"http://wx.qlogo.cn/mmopen/vi_32/aSKcBBPpibyKNicHNTMM0qJVh8Kjgiak2AHWr8MHM4WgMEm7GFhsf8OYrySdbvAMvTsw3mo8ibKicsnfN5pRjl1p8HQ/0\","
			+ "\"unionId\":\"ocMvos6NjeKLIBqg5Mr9QjxrP1FA\","
			+ "\"watermark\":{\"timestamp\":1477314187,"
			+ "\"appid\":\"wx4f4bc4dec97d474b\"}}\r\n";

	/**
	 * 微信小程序AES加密数据解密算法java实现
	 * 
	 * @throws Exception
	 */
	@Test
	public void testWxappDecryptData() throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(Base64.decodeBase64(sessionKey), "AES");

		IvParameterSpec ivSpec = new IvParameterSpec(Base64.decodeBase64(iv));

		Cipher cipher = Cipher.getInstance(AEC_CBC_128);

		cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivSpec);

		byte[] data = cipher.doFinal(Base64.decodeBase64(encryptedData));

		String decryptedData = new String(data, "utf-8");

		JSONObject object = new JSONObject(decryptedData);

		assertThat(appId, equalTo(object.getJSONObject("watermark").getString("appid")));

	}
}
