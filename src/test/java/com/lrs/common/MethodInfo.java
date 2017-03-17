package com.lrs.common;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
//注解使用范围枚举
import static java.lang.annotation.ElementType.*;

/**
 * 元注解：用来描述注解的注解
 * 
 * 参考文档：http://www.importnew.com/23816.html
 * 
 * @author Swedish-li
 *
 */
// 描述注解使用范围
@Target({ TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE })
// 表示注解可以被javadoc文档化
@Documented
// 标明注解可以被继承
@Inherited
// 标明注解的存在时期
@Retention(RetentionPolicy.RUNTIME)

public @interface MethodInfo {
	String value() default "";
}
