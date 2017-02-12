package com.lrs.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;

//hibernate validator配置国际化支持时直接输出键值
//问题链接：https://www.oschina.net/question/1243908_2146076
public class UserVo {
	@Size(min=2,max = 6,message="{size.u.name.len}")
	//@Size(min=2,max = 6)
	private String name;
	@Max(value=1,message="{max.sex}")
	//@Max(value=1)
	private int sex;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
}
