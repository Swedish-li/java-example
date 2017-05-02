package com.lrs.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lrs.model.Brand;
import com.lrs.model.query.BrandQuery;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class BrandDaoImplTest {

	@Resource
	private BrandDao brandDao;

	@Test
	public void testSelectBrandListByQuery() {
		BrandQuery brandQuery = new BrandQuery();
		brandQuery.setName("尔");
		brandQuery.setIsDisplay(1);
		List<Brand> list = brandDao.selectBrandListByQuery(brandQuery);
		assertNotNull(list);
		assertEquals(3, list.size());
	}

	@Transactional
	@Rollback
	@Test
	public void testDelete() {
		brandDao.delete(new Long[] { 10L, 11L, 12L });
		List<Brand> list = brandDao.selectBrandListByQuery(new BrandQuery());
		assertEquals(17, list.size());
	}

	@Test
	public void testSelectBrand() {
		Brand brand = brandDao.selectBrand(1L);
		assertEquals(Long.valueOf(1L), brand.getId());
		assertEquals("依琦莲", brand.getName());
		assertEquals(Integer.valueOf(1), brand.getIsDisplay());
	}
}
