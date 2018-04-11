package com.lrs.common.model;

import com.lrs.common.utils.MapAdapter;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Map;

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
