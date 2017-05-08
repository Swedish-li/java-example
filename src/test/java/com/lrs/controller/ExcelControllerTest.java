package com.lrs.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;

public class ExcelControllerTest extends SimpleBaseTest {

	@Before
	public void setUp() {
		initMvc();
	}

	@Test
	public void testExportExcel() throws Exception {
		mockMvc.perform(get("/excel/export"))
				.andExpect(status().isOk())
				.andExpect(view().name("excelView"));
	}

}
