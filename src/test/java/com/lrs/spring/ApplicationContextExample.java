package com.lrs.spring;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ApplicationContextExample {

	/**
	 * 在Web环境中通过HttpServletRequest获取ApplicationContext
	 */

	ApplicationContext getApplicationContext(HttpServletRequest req) {

		// 获取通过 org.springframework.web.context.ContextLoaderListener 加载的容器
		return WebApplicationContextUtils.getWebApplicationContext(getServletContext(req));
	}

	/**
	 * 根据在Servlet上下文中的属性名来获取ApplicationContext
	 * 
	 * @param req
	 * @param attrName
	 * @return
	 */
	ApplicationContext getApplicationContext(HttpServletRequest req, String attrName) {
		return WebApplicationContextUtils.getWebApplicationContext(getServletContext(req), attrName);
	}

	private ServletContext getServletContext(HttpServletRequest req) {
		return req.getServletContext();
	}
}
