package com.lrs.controller;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lrs.model.Brand;
import com.lrs.service.BrandService;

@Controller
@RequestMapping("brand")
public class BrandController {

	@Resource
	private BrandService brandService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Brand> getBrand(@PathVariable("id") Long id) {
		Brand brand = brandService.getBrand(id);
		return ResponseEntity.ok(brand);
	}

}
