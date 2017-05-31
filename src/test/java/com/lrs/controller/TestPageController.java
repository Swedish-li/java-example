package com.lrs.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Spring Mvc 的单元测试
 * 
 * @author Swedish-li
 *
 */
public class TestPageController extends SimpleBaseTest {

	@Before
	public void setUp() {
		initMvc();
	}
	
	@Test
	public void testGetMap() throws Exception {
		mockMvc.perform(get("/view/get-map"))
			.andDo(log())
			.andExpect(status().isOk());
		
	}
	
	// java.lang.ClassNotFoundException: javax.servlet.SessionCookieConfig
	// 更换Servlet-api 版本
	@Test
	// (expected = MethodArgumentTypeMismatchException.class)
	// 400 bad request ：错误请求 — 请求中有语法问题，或不能满足请求。
	public void testCovert() throws Exception {
		mockMvc.perform(post("/view/convert-int") // post请求
				.param("start", "abc")) // 设置请求参数
				.andDo(print()) // 请求响应内容输出
				.andExpect(status().is(400)); // 响应状态断言
	}

	@Test
	public void testCovertSucc() throws Exception {
		mockMvc.perform(post("/view/convert-int")
				.param("start", "6")) // 请求参数
				.andDo(print()) // 请求响应内容输出
				.andExpect(content().contentType("text/plain;charset=UTF-8")) // contentType
				.andExpect(status().isOk());

	}

	// Json断言依赖<a href="https://github.com/jayway/JsonPath">JsonPath</a>
	// expression
	@Test
	public void testGetBrand() throws Exception {
		mockMvc.perform(post("/view/get-brand"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.id").value(5))
				.andExpect(jsonPath("$.name").value("test"))
				.andExpect(jsonPath("$.description").value("test desc"))
				.andExpect(jsonPath("$.imgUrl").value("imgUrl"))
				.andExpect(jsonPath("$.sort").value(10))
				.andExpect(jsonPath("$.isDisplay").value(1));
	}
}
