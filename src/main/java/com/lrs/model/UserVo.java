package com.lrs.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Accessors(chain = true)
public class UserVo {
    @Size(min = 2, max = 6, message = "{size.u.name.len}")
    @NotNull(message = "{name.required}")
    private String name;
    @Max(value = 1, message = "{max.sex}")
    // @Max(value=1)
    private int sex;

}
