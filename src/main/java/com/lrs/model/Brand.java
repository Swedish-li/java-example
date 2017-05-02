package com.lrs.model;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class Brand implements Serializable {

	private static final long serialVersionUID = -2236741466672571896L;
	@NonNull
	private Long id;

	private String name;

	private String description;

	private String imgUrl;

	private Integer sort;

	private Integer isDisplay;

}
