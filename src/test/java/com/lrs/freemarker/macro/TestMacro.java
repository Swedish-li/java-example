package com.lrs.freemarker.macro;

import org.junit.Test;

import com.lrs.freemarker.TestBase;

public class TestMacro extends TestBase {
	private String maroName = "test_marco.ftl";

	@Test
	public void testFirstMarco() {
		process(getRoot(), maroName);
	}
}
