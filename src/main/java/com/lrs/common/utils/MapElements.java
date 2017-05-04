package com.lrs.common.utils;

import javax.xml.bind.annotation.XmlElement;

import com.lrs.common.model.People;

public class MapElements {
	@XmlElement
	public String key;

	@XmlElement
	public People user;

	public MapElements() {
	}

	public MapElements(String key, People user) {
		this.key = key;
		this.user = user;
	}
}