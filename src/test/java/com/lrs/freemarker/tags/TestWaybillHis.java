package com.lrs.freemarker.tags;

import com.lrs.freemarker.TestBase;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TestWaybillHis extends TestBase {
    private String templateName = "waybillHis.ftl";

    private Map<String, Object> getModelMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("office_name", "dlb");
        map.put("user_name", "管理员");
        map.put("tel", "13520612358");
        map.put("next_station", "华东分拣");

        return map;
    }

    @Test
    public void testWaybill() {
        process(getModelMap(), templateName);
    }
}
