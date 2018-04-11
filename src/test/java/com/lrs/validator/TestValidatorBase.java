package com.lrs.validator;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.junit.BeforeClass;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * spring整合 hibernate validator : http://hualong.iteye.com/blog/2038092
 *
 * @author liruishi
 */
public class TestValidatorBase {

    /**
     * JSR 303 规范中所有内置 constraint
     *
     * @Null 被注释的元素必须为 null
     * @NotNull 被注释的元素必须不为 null
     * @AssertTrue 被注释的元素必须为 true
     * @AssertFalse 被注释的元素必须为 false
     * @Min(value) 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
     * @Max(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
     * @DecimalMin(value) 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
     * @DecimalMax(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值 @Size(max=, min=)
     * 被注释的元素的大小必须在指定的范围内
     * @Digits (integer, fraction) 被注释的元素必须是一个数字，其值必须在可接受的范围内
     * @Past 被注释的元素必须是一个过去的日期
     * @Future 被注释的元素必须是一个将来的日期 @Pattern(regex=,flag=) 被注释的元素必须符合指定的正则表达式
     */
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
    /**
     * Hibernate Validator 附加的 constraint
     *
     * @NotBlank(message =) 验证字符串非null，且长度必须大于0
     * @Email 被注释的元素必须是电子邮箱地址 @Length(min=,max=) 被注释的字符串的大小必须在指定的范围内
     * @NotEmpty 被注释的字符串的必须非空 @Range(min=,max=,message=) 被注释的元素必须在合适的范围内
     */

}
