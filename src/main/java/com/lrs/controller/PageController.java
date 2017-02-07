package com.lrs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
