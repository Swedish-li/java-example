package com.lrs.validator.example;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.Test;

import com.lrs.validator.TestValidatorBase;
import com.lrs.validator.model.Car;

public class TestCarValidate extends TestValidatorBase {
	@Test
	public void testManufactureIsNull() {
		Car car = new Car(null, "DD-AB-123", 4);
		Set<ConstraintViolation<Car>> constraintViolations = validator.validate(car);
		assertEquals(1, constraintViolations.size());
		// 提示信息会根据语言环境变化
		// 国际化信息保存在hibernate-validator-5.3.4.Final.jar中的org\hibernate\validator文件夹下
		assertEquals("不能为null", constraintViolations.iterator().next().getMessage());
	}

	@Test
	public void licensePlateTooShort() {
		Car car = new Car("Morris", "D", 4);
		Set<ConstraintViolation<Car>> sets = validator.validate(car);
		assertEquals(1, sets.size());
		String msg = sets.iterator().next().getMessage();
		assertEquals("个数必须在2和14之间", msg);

	}

	@Test
	public void seatCountTooLow() {
		Car car = new Car("Morris", "DD-AB-123", 1);
		Set<ConstraintViolation<Car>> sets = validator.validate(car);
		assertEquals(1, sets.size());
		String msg = sets.iterator().next().getMessage();
		assertEquals("最小不能小于2", msg);
	}

	@Test
	public void carIsValid() {
		Car car = new Car("Morris", "DD-AB-123", 2);
		Set<ConstraintViolation<Car>> sets = validator.validate(car);
		assertEquals(0, sets.size());
	}
}
