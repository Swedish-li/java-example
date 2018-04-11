package com.lrs.freemarker.datebase;

import com.lrs.common.loader.DatabaseTemplateloader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/templateloader-app.xml"})
public class TestDatabaseTemplateLoader {
    @Resource
    private DatabaseTemplateloader templateLoader;

    @Test
    public void testDatabase() throws Exception {
        Configuration config = new Configuration(Configuration.VERSION_2_3_23);
        config.setTemplateLoader(templateLoader);
        Map<String, Object> root = new HashMap<>();
        root.put("name", "freemarker");
        root.put("msg", "您已经完成了第一个FreeMarker的示例");
        // config.setDirectoryForTemplateLoading(new File("d:\\"));
        // Template t = config.getTemplate("key",Locale.CANADA);
        String templateKey = "key";
        Template t = config.getTemplate(templateKey);
        StringWriter writer = new StringWriter();
        t.process(root, writer);

        System.out.println(writer.toString());
        writer.flush();
    }
}
