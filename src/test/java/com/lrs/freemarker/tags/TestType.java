package com.lrs.freemarker.tags;

import com.lrs.freemarker.TestBase;
import org.junit.Test;

import java.util.Date;
import java.util.Map;

public class TestType extends TestBase {
    private final static String templateName = "type.ftl";

    @Test
    public void testDate() {

        Map<String, Object> map = getRoot();
        map.put("date", new Date());
        process(map, templateName);
    }
}
