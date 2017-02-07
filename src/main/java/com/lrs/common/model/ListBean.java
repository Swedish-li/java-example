package com.lrs.common.model;


import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "userList")
public class ListBean {
	private String name;

	private List<People> list;

	@XmlElements({ 
		@XmlElement(name = "user", type = People.class)
	})
	
	public List<People> getList() {
		return list;
	}

	public void setList(List<People> list) {
		this.list = list;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
