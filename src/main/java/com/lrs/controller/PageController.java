package com.lrs.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lrs.model.Brand;

//   /views/warehouse/warehouse
@RestController
@RequestMapping("view")
public class PageController {

	/**
	 * 数据类型转换注入
	 * 
	 * 当类型转换出错时Spring会抛出以下异常
	 * 
	 * org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
	 * 
	 * java.lang.NumberFormatException
	 * 
	 * 当抛出次异常时Tomcat的响应状态
	 * 
	 * HTTP Status 400
	 * 
	 * StringHttpMessageConverter内部使用ISO-8859-1
	 * 
	 * @param start
	 */
	@RequestMapping(value = "convert-int")
	public String toInt(int start) {
		return "convert to test 上海!";
	}

	/**
	 * Json响应
	 * 
	 * @return
	 */
	@RequestMapping(value = "get-brand")
	public Brand getBrand() {
		Brand brand = new Brand()
				.setId(5L)
				.setName("test")
				.setDescription("test desc")
				.setImgUrl("imgUrl")
				.setIsDisplay(1)
				.setSort(10);

		return brand;
	}
	@RequestMapping("get-map")
	public Map<String, Object> getMap() {
		Map<String, Object> map = new HashMap<>();
		map.put("decimal", String.valueOf(new BigDecimal(1.57788).doubleValue()));
		map.put("date", new Date());
		map.put("double", 1.879);
		return map;
	}
}
