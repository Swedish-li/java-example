package com.lrs.service;

import com.lrs.mapper.BrandMapper;
import com.lrs.model.Brand;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BrandService {

    @Resource
    private BrandMapper mapper;

    public Brand getBrand(Long id) {
        return mapper.selectBrand(id);
    }
}
