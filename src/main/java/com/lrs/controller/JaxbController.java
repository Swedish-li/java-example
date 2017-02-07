package com.lrs.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lrs.common.model.ListBean;
import com.lrs.common.model.MapBean;
import com.lrs.common.model.People;
import com.lrs.common.model.People.Account;
import com.lrs.common.model.People.Cards;

@Controller
@RequestMapping("jaxb")
public class JaxbController {

	/**
	 * 将对象转为xml
	 */
	@RequestMapping("object2xml")
	public ModelAndView object2xml() {
		ModelAndView mav = new ModelAndView("jaxb2MarshallingView");
		People user = new People();
		user.name = "zhangsan";
		user.id = "1";
		user.address = "shenzhen";
		user.age = 20;
		user.sex = "man";

		user.account = new Account("zhang", "abc123");

		List<String> cards = new ArrayList<String>();
		cards.add("gonghang");
		cards.add("jianhang");
		user.cards = new Cards(cards);

		mav.addObject(user);
		return mav;
	}

	/**
	 * 将list转为xml
	 */
	@RequestMapping("list2xml")
	public ModelAndView list2xml() {
		ModelAndView mav = new ModelAndView("jaxb2MarshallingView");
		List<People> userList = new ArrayList<People>();
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

			userList.add(user);
		}

		ListBean listBean = new ListBean();
		listBean.setList(userList);
		mav.addObject(listBean);
		return mav;
	}

	/**
	 * 将map转为xml Content-Type:application/xml;charset=UTF-8
	 */
	@RequestMapping("map2xml")
	public ModelAndView map2xml() {
		ModelAndView mav = new ModelAndView("jaxb2MarshallingView");
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
			boolean flag = true;
			if (flag) {
				throw new RuntimeException("这是一个测试运行期异常！");
			}
			//int c = 1 / 0;
			map.put("1", user);
		}
		mapBean.setMap(map);
		mav.addObject(mapBean);
		return mav;
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
