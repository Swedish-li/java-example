package com.lrs.common.utils;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.lrs.common.model.People;

public class MapAdapter extends XmlAdapter<MapElements[], Map<String, People>> {

	@Override
	public MapElements[] marshal(Map<String, People> map) throws Exception {
		MapElements[] mapElements = new MapElements[map.size()];
		int i = 0;
		for (Map.Entry<String, People> entry : map.entrySet()) {
			mapElements[i++] = new MapElements(entry.getKey(), entry.getValue());
		}
		return mapElements;
	}

	@Override
	public Map<String, People> unmarshal(MapElements[] elements) throws Exception {
		Map<String, People> map = new HashMap<String, People>();
		for (MapElements mapelement : elements) {
			map.put(mapelement.key, mapelement.user);
		}
		return map;
	}

}
