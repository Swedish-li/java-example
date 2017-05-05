package com.lrs.controller;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

/**
 * 集成测试
 * 
 * @author Swedish-li
 *
 */

public class BrandControllerTest extends SimpleBaseTest {
	// application/json;UTF-8
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Before
	public void setUp() {
		initMvc();
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
