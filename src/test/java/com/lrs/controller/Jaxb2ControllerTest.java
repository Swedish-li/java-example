package com.lrs.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

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
				.andDo(print())
				.andExpect(status().isOk());

	}

	@Test
	public void testMap2xml() throws Exception {
		mockMvc.perform(get("/jaxb/map2xml").accept(MediaType.APPLICATION_XML))
				.andDo(print())
				.andExpect(status().isOk());
	}

}
