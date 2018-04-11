package com.lrs.common.utils;

import com.lrs.common.model.People;

import javax.xml.bind.annotation.XmlElement;

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