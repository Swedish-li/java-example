package com.lrs.freemarker.tags;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.lrs.freemarker.TestBase;

public class TestTags1 extends TestBase {

	private String templateName = "tags.ftl";

	@Test
	public void testIfTags() {
		Map<String, Object> root = getRoot();
		root.put("user", "jackie chen");
		root.put("price", 10);
		process(root, templateName);
	}

	@Test
	public void testList() {
		Object[] users = { "jackie", "mike", "tom", "john" };
		Map<String, Object> root = getRoot();
		root.put("users", users);
		List<String> fruits = Arrays.asList("apple", "banana", "lemon");
		root.put("fruits", fruits);
		process(root, templateName);
	}

	@Test
	public void testInclude() {
		process(getRoot(), templateName);
	}
	
	

}
