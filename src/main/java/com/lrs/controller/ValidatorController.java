package com.lrs.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lrs.model.UserVo;

@Controller
@RequestMapping("valid")
public class ValidatorController {

	private final static Logger logger = LoggerFactory.getLogger(ValidatorController.class);

	@RequestMapping("valid-user")
	@ResponseBody
	public String validUserVo(@Valid @ModelAttribute UserVo user, BindingResult rs, ModelMap model) {
		logger.info("user:{}", user);
		logger.info("bind result:{}\nerror:{}", rs, rs.getFieldErrors());
		List<FieldError> errors = rs.getFieldErrors();
		for (FieldError error : errors) {
			String msg = error.getDefaultMessage();
			String code = error.getCode();
			logger.error("code:{},msg:{}", code, msg);
		}
		logger.info("model map:{}", model);
		if (rs.hasErrors()) {
			return "error";
		}
		return "page";
	}
	/**
	 * user:com.lrs.model.UserVo@6e5ca6ab[name=li,sex=1] bind
	 * result:org.springframework.validation.BeanPropertyBindingResult: 0 errors
	 * model map:{userVo=com.lrs.model.UserVo@6e5ca6ab[name=li,sex=1],
	 * org.springframework.validation.BindingResult.userVo=org.springframework.validation.BeanPropertyBindingResult:
	 * 0 errors}
	 * 
	 */

}
