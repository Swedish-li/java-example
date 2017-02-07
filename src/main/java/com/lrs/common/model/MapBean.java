package com.lrs.common.model;

import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.lrs.common.utils.MapAdapter;


@XmlRootElement(name = "userMap")
public class MapBean {
	private Map<String, People> map;
    
    @XmlJavaTypeAdapter(MapAdapter.class)
    public Map<String, People> getMap() {
        return map;
    }
    public void setMap(Map<String, People> map) {
        this.map = map;
    }
}
