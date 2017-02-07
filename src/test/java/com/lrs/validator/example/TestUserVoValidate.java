package com.lrs.validator.example;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.Test;

import com.lrs.validator.TestValidatorBase;
import com.lrs.validator.model.UserVo;

import static org.junit.Assert.*;

public class TestUserVoValidate extends TestValidatorBase {
	@Test
	public void testSizeName() {
		UserVo user1 = new UserVo().setName("L").setSex(1);
		Set<ConstraintViolation<UserVo>> sets = validator.validate(user1);
		assertEquals(1, sets.size());
		String msg = sets.iterator().next().getMessage();
		assertEquals("用户名不能为空长度在2和6之间", msg);
	}

	@Test
	public void testMaxSex() {
		UserVo user = new UserVo().setName("jack").setSex(3);
		Set<ConstraintViolation<UserVo>> sets = validator.validate(user);
		assertEquals(1, sets.size());
		String msg = sets.iterator().next().getMessage();
		assertEquals("超过最大值1", msg);
	}
}
