package com.lrs.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lrs.model.UserVo;

@Controller
@RequestMapping("valid")
public class ValidatorController {
	@RequestMapping("valid-user")
	public String validUserVo(@Valid @ModelAttribute UserVo user, BindingResult rs, ModelMap model) {
		
		if(rs.hasErrors()){
			return "error";
		}
		return "page";
	}

}
