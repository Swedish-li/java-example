package com.lrs.spring.spel.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Inventor {

	private String name;

	private String nationality;

	private Date birthday;
}
