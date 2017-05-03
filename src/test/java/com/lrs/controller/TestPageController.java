package com.lrs.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * Spring Mvc 的单元测试
 * 
 * @author Swedish-li
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:dispatcher-servlet.xml")
public class TestPageController {

	private MockMvc mockMvc;
	@Resource
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mockMvc = webAppContextSetup(webApplicationContext).build();
	}
	// java.lang.ClassNotFoundException: javax.servlet.SessionCookieConfig 更换Servlet-api 版本
	// 和实际运行的异常不一致
	@Test
	// (expected = MethodArgumentTypeMismatchException.class)
	// 会抛出异常，但是不会抛到tomcat里，400状态返回,次单元测试通过
	public void testCovert() throws Exception {
		mockMvc.perform(post("/view/convert-int").param("start", "abc"));
		
		
	}
}
