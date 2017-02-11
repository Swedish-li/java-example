package com.lrs.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class UserVo {
	@Size(min=2,max = 6,message="{size.u.name.len}")
	private String name;
	@Max(value=1,message="{max.sex}")
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
