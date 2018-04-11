package com.lrs.controller;

import org.junit.Before;
import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

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
