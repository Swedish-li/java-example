package com.lrs.common.json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

/**
 * 读取json目录中的json数据，输出字符串
 * 
 * @author Swedish-li
 *
 */
public class Util {
	private final static String BASE_PATH = Util.class.getResource("/").getPath() + File.separator + "json";
	
	/**
	 * 获取文件中的JSON字符串
	 * @param name
	 * @return
	 */
	public static String getJson(String name) {
		String path = BASE_PATH + File.separator + name + ".json";
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(new File(path)));
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				stringBuilder.append(str);
			}
			
			return stringBuilder.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}

		
	}

	@Test
	public void testUtil() {
		String str = getJson("CustomerRights");
		System.out.println(str);
	}
}
