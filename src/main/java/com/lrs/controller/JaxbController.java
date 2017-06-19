package com.lrs.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.MediaType.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lrs.common.model.ListBean;
import com.lrs.common.model.MapBean;
import com.lrs.common.model.People;
import com.lrs.common.model.People.Account;
import com.lrs.common.model.People.Cards;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping("jaxb")
public class JaxbController {
	@RequestMapping(value = "example", method = POST, consumes = APPLICATION_XML_VALUE, produces = APPLICATION_XML_VALUE)
	public ResponseEntity<People> show(@RequestBody People people) {
		return ResponseEntity.ok(people);
	}

	/**
	 * 将list转为xml
	 */
	@RequestMapping(value = "list2xml", method = GET, produces = APPLICATION_XML_VALUE)
	public ResponseEntity<ListBean> list2xml() {
		List<People> userList = new ArrayList<People>();
		for (int i = 0; i < 2; i++) {
			People user = new People();
			user.name = "zhangsan" + i;
			user.id = "1" + i;
			user.address = "shenzhen";
			user.age = 20 + i;
			user.sex = "man";
			user.account = new Account("zhang" + i, "abc123");
			List<String> cards = new ArrayList<String>();
			cards.add("gonghang" + i);
			cards.add("jianhang" + i);
			user.cards = new Cards(cards);

			userList.add(user);
		}
		ListBean listBean = new ListBean();
		listBean.setList(userList);

		return ResponseEntity.ok(listBean);
	}

	/**
	 * 将map转为xml Content-Type:application/xml;charset=UTF-8
	 */
	@RequestMapping(value = "map2xml", method = GET, produces = APPLICATION_XML_VALUE)
	public ResponseEntity<MapBean> map2xml() {
		MapBean mapBean = new MapBean();
		HashMap<String, People> map = new HashMap<String, People>();

		for (int i = 0; i < 2; i++) {
			People user = new People();
			user.name = "zhangsan" + i;
			user.id = "1";
			user.address = "shenzhen";
			user.age = 20;
			user.sex = "man";
			user.account = new Account("zhang" + i, "abc123");
			List<String> cards = new ArrayList<String>();
			cards.add("gonghang" + i);
			cards.add("jianhang" + i);
			user.cards = new Cards(cards);

			// int c = 1 / 0;
			map.put("1", user);
		}
		mapBean.setMap(map);

		return ResponseEntity.ok(mapBean);
	}

	// 优先处理更具体的异常
	@ExceptionHandler(ArithmeticException.class)
	public void exceptionHandler(ArithmeticException ae, HttpServletResponse res, HttpServletRequest req) {
		try {
			ae.printStackTrace();
			res.getWriter().print(ae.getMessage() + "这是一个测试信息");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@ExceptionHandler(Exception.class)
	public void allExceptionHandler(Exception e, HttpServletResponse res, HttpServletRequest req) {
		System.out.println("allExceptionHandler");
		try {
			e.printStackTrace();
			res.getWriter().println("<h1>this is an allException Handler</h1>");
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}

}
