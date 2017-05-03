package com.lrs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//   /views/warehouse/warehouse
@Controller
@RequestMapping("view")
public class PageController {
	@RequestMapping(value = "{pageDir}/{pageName}")
	public String toPage(@PathVariable(name = "pageDir") String pageDir,
			@PathVariable(name = "pageName") String pageName) {
		return pageDir + "/" + pageName;
	}

	@RequestMapping(value = "{pageDir}/{pageSubDir}/{pageName}")
	public String toPage(@PathVariable(name = "pageDir") String pageDir,
			@PathVariable(name = "pageSubDir") String pageSubDir, @PathVariable(name = "pageName") String pageName) {
		return pageDir + "/" + pageSubDir + "/" + pageName;
	}

	@RequestMapping(value = "{pageName}")
	public String toPage(@PathVariable(name = "pageName") String pageName) {
		return pageName;
	}

	/**
	 * 数据类型转换注入
	 * 
	 * 当类型转换出错时Spring会抛出以下异常
	 * 
	 * org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
	 * 
	 * java.lang.NumberFormatException
	 * 
	 * 当抛出次异常时Tomcat的响应状态
	 * 
	 * HTTP Status 400
	 * 
	 * @param start
	 */
	@RequestMapping("convert-int")
	@ResponseBody
	public String toInt(int start) {
		return "convert to test sucess!";
	}
}
