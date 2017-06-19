package com.lrs.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.hamcrest.Matchers.*;

public class Jaxb2ControllerTest extends SimpleBaseTest {

	@Before
	public void setUp() {
		initMvc();
	}

	@Test
	public void testShow() throws Exception {
		mockMvc.perform(
				post("/jaxb/example")
						.content(getExampleXml())
						.contentType(MediaType.APPLICATION_XML))
				.andExpect(status().isOk())
				.andDo(log()); // 将MvcResult输出到日志DEBUG级别
	}

	private String getExampleXml() {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<user id='10' name='test' age='18' sex='1'>"
				+ "<address>ShangHai</address>"
				+ "<Account username='test-account'>123456</Account>"
				+ "<Cards>"
				+ "<card>sh</card>"
				+ "<card>nj</card>"
				+ "</Cards>"
				+ "</user>";
	}

	@Test
	public void testList2xml() throws Exception {
		mockMvc.perform(get("/jaxb/list2xml").accept(MediaType.APPLICATION_XML))
				.andDo(log())
				.andExpect(status().isOk())
				.andExpect(xpath("/userList/user").nodeCount(2))
				.andExpect(xpath("/userList/user[1]//@id").string(equalTo("10")))
				.andExpect(xpath("/userList/user[1]//@name").string(equalTo("zhangsan0")))
				.andExpect(xpath("/userList/user[1]//@age").string(equalTo("20")))
				.andExpect(xpath("/userList/user[1]//@sex").string(equalTo("man")))
				.andExpect(xpath("/userList/user[1]/address/text()").string(equalTo("shenzhen")))
				.andExpect(xpath("/userList/user[1]/Account//@username").string(equalTo("zhang0")))
				.andExpect(xpath("/userList/user[1]/Account/text()").string(equalTo("abc123")))
				.andExpect(xpath("/userList/user[1]/Cards/card[1]").string(equalTo("gonghang0")))
				.andExpect(xpath("/userList/user[1]/Cards/card[2]").string("jianhang0"))
				// =============================================================================
				.andExpect(xpath("/userList/user[2]//@id").string(equalTo("11")))
				.andExpect(xpath("/userList/user[2]//@name").string(equalTo("zhangsan1")))
				.andExpect(xpath("/userList/user[2]//@age").string(equalTo("21")))
				.andExpect(xpath("/userList/user[2]//@sex").string(equalTo("man")))
				.andExpect(xpath("/userList/user[2]/address/text()").string(equalTo("shenzhen")))
				.andExpect(xpath("/userList/user[2]/Account//@username").string(equalTo("zhang1")))
				.andExpect(xpath("/userList/user[2]/Account/text()").string(equalTo("abc123")))
				.andExpect(xpath("/userList/user[2]/Cards/card[1]").string(equalTo("gonghang1")))
				.andExpect(xpath("/userList/user[2]/Cards/card[2]").string("jianhang1"));

	}

	@Test
	public void testMap2xml() throws Exception {
		mockMvc.perform(get("/jaxb/map2xml").accept(MediaType.APPLICATION_XML))
				.andExpect(xpath("/userMap/map/item/key[1]/text()").string(equalTo("1")))
				.andExpect(xpath("/userMap/map/item/user[1]//@id").string(equalTo("1")))
				.andExpect(xpath("/userMap/map/item/user[1]//@name").string(equalTo("zhangsan1")))
				.andExpect(xpath("/userMap/map/item/user[1]//@age").string(equalTo("20")))
				.andExpect(xpath("/userMap/map/item/user[1]//@sex").string(equalTo("man")))
				.andExpect(xpath("/userMap/map/item/user[1]/address/text()").string(equalTo("shenzhen")))
				.andExpect(xpath("/userMap/map/item/user[1]/Account/text()").string(equalTo("abc123")))
				.andExpect(xpath("/userMap/map/item/user[1]/Account//@username").string(equalTo("zhang1")))
				.andExpect(xpath("/userMap/map/item/user[1]/Cards/card[1]/text()").string(equalTo("gonghang1")))
				.andExpect(xpath("/userMap/map/item/user[1]/Cards/card[2]/text()").string(equalTo("jianhang1")))
				.andDo(log())
				.andExpect(status().isOk())
				.andReturn();
	}

}
