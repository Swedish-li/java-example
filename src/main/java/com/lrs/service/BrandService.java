package com.lrs.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lrs.dao.BrandDao;
import com.lrs.model.Brand;

@Service
public class BrandService {

	@Resource
	private BrandDao brandDao;

	public Brand getBrand(Long id) {
		return brandDao.selectBrand(id);
	}
}
