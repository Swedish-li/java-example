package com.lrs.controller;

import java.nio.charset.Charset;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

/**
 * 集成测试
 * 
 * @author Swedish-li
 *
 */
@WebAppConfiguration(value = "src/main/webapp")
@ContextHierarchy({
		@ContextConfiguration(name = "parent", locations = "classpath:applicationContext.xml"),
		@ContextConfiguration(name = "child", locations = "classpath:dispatcher-servlet.xml")
})
@RunWith(SpringJUnit4ClassRunner.class)
public class BrandControllerTest {
	// application/json;UTF-8
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	@Resource
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testGetBrand() throws Exception {
		mockMvc.perform(get("/brand/5"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.id").value(5))
				.andExpect(jsonPath("$.name").value("菩媞"));
	}

}
