package com.lrs.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

//hibernate validator配置国际化支持时直接输出键值
//问题链接：https://www.oschina.net/question/1243908_2146076
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class UserVo {
	@Size(min = 2, max = 6, message = "{size.u.name.len}")
	// @Size(min=2,max = 6)
	private String name;
	@Max(value = 1, message = "{max.sex}")
	// @Max(value=1)
	private int sex;

}
