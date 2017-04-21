package com.lrs.common.json;

import java.math.BigDecimal;
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

}
