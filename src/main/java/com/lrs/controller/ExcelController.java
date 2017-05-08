package com.lrs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("excel")
public class ExcelController {
	
	@GetMapping("export")
	public ModelAndView exportExcel() {
		return new ModelAndView("excelView");
	}
}
