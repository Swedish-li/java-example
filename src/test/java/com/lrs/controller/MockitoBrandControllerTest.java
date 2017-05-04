package com.lrs.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.lrs.model.Brand;
import com.lrs.service.BrandService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

/**
 * 使用Mockito进行独立环境的单元测试
 * 
 * @author Swedish-li
 *
 */
public class MockitoBrandControllerTest {
	@Mock
	private BrandService brandService;

	@InjectMocks
	private BrandController brandController;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = standaloneSetup(brandController).build();
	}

	@Test
	public void testGetBrand() throws Exception {
		// 测试数据
		Brand brand = new Brand(5L)
				.setName("菩媞")
				.setDescription("test desc")
				.setImgUrl("img url")
				.setIsDisplay(1)
				.setSort(15);

		Long id = 5L;
		// 设置mock对象预期调用的方法及返回值
		when(brandService.getBrand(id)).thenReturn(brand);

		mockMvc.perform(get("/brand/5").accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.id").value(5))
				.andExpect(jsonPath("$.imgUrl").value("img url"))
				.andExpect(jsonPath("$.name").value("菩媞"))
				.andExpect(jsonPath("$.description").value("test desc"))
				.andExpect(jsonPath("$.isDisplay").value(1))
				.andExpect(jsonPath("$.sort").value(15));

		// 方法调用的验证，而它关注点则在mock对象的交互行为上，比如验证mock对象的某个方法调用参数，调用次数，顺序等等
		verify(brandService, times(1)).getBrand(id);
		verifyNoMoreInteractions(brandService);
	}
}
