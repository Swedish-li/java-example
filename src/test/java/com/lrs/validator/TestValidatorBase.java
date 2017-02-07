package com.lrs.validator;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.junit.BeforeClass;

/**
 * spring整合 hibernate validator : http://hualong.iteye.com/blog/2038092
 * @author liruishi
 *
 */
public class TestValidatorBase {
	protected static Validator validator;

	@BeforeClass
	public static void setUpValidator() {
		// 配置用户自定义语言信息
		// ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		ValidatorFactory factory = Validation.byDefaultProvider().configure()
				.messageInterpolator(
						new ResourceBundleMessageInterpolator(new PlatformResourceBundleLocator("message")))
				.buildValidatorFactory();
		// A Validator instance is thread-safe and may be reused multiple times.
		validator = factory.getValidator();

	}

}
