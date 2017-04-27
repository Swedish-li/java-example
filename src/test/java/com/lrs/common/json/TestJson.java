package com.lrs.common.json;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

/**
 * Json数据解析测试
 * 
 * JSONObject : Json对象 JSONArray : Json数组
 * 
 * @author Swedish-li
 *
 */
public class TestJson {
	@Test
	public void testCustomerRights() {
		String res = Util.getJson("CustomerRights");

		// 使用构造器将json字符串，Map,Bean 构造此对象
		JSONObject jsonObject = new JSONObject(res);
		System.out.println(jsonObject);

		JSONObject data = (JSONObject) jsonObject.getJSONObject("data");

		JSONArray list = data.getJSONArray("list");

		// 不可以这样调用 org.json.JSONException: JSONObject["list"] not a string.
		// String strArr = data.getString("list");
		// System.out.println("str arr:"+ strArr);

		// System.out.println(list);

		JSONObject item = (JSONObject) list.get(0);

		System.out.println(item);

	}

	@Test
	public void testCouponAccount() {
		String res = Util.getJson("CouponAccount");
		JSONObject jsonObject = new JSONObject(res);
		System.out.println(jsonObject);
	}

	// 将Map转换为JSON
	@Test
	public void map2json() {
		Map<String, Object> map = new HashMap<>();
		map.put("name", "map2json");
		map.put("date", new Date());
		map.put("count", 1);
		map.put("price", new BigDecimal(1.32));

		JSONObject object = new JSONObject(map);

		System.out.println(object.toString());
	}

	@Test
	public void testGithubRestful() throws ParseException {
		String res = Util.getJson("github");
		JSONArray jsonArray = new JSONArray(res);
		JSONObject jsonObject = jsonArray.getJSONObject(0);
		// YYYY-MM-DDTHH:MM:SSZ iso 8601
		// yyyy-MM-dd'T'HH:mm:ssZ Java
		String dateStr = jsonObject.getString("updated_at");
		// 2017-03-29T21:12:22Z
		System.out.println(parse(dateStr));
	}
	/**
	 * DateTimeFormat.ISO不安全兼容ISO 8601
	 * 
	 * http://blog.csdn.net/alpslzy/article/details/56293789
	 * 
	 * @param str
	 * @return
	 */
	private Date parse(String str) {
		Calendar canlendar = javax.xml.bind.DatatypeConverter.parseDateTime(str);
		Date dt = canlendar.getTime();
		String formatted = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(dt);
		// 2017-03-30T05:12:22+0800
		System.out.println(formatted);
		return dt;
	}

}
