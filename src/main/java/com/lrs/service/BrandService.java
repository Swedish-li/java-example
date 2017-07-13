package com.lrs.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lrs.mapper.BrandMapper;
import com.lrs.model.Brand;

@Service
public class BrandService {

	@Resource
	private BrandMapper mapper;

	public Brand getBrand(Long id) {
		return mapper.selectBrand(id);
	}
}
