package com.lrs.validator.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

public class UserVo {
    @Size(min = 2, max = 6, message = "{size.u.name.len}")
    private String name;
    @Max(value = 1, message = "{max.sex}")
    private int sex;

    public String getName() {
        return name;
    }

    public UserVo setName(String name) {
        this.name = name;
        return this;
    }

    public int getSex() {
        return sex;
    }

    public UserVo setSex(int sex) {
        this.sex = sex;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
