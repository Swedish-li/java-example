package com.lrs.freemarker;

import freemarker.core.ParseException;
import freemarker.template.*;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class TestBase {
    private final static Version VERSION_2_3_23 = new Version("2.3.23");
    private Configuration cfg = new Configuration(VERSION_2_3_23);

    {
        cfg.setObjectWrapper(new DefaultObjectWrapperBuilder(VERSION_2_3_23).build());
        URL url = this.getClass().getResource("/");
        try {
            // 加载模板文档
            cfg.setDirectoryForTemplateLoading(new File(url.getFile() + "template"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取根map
     *
     * @return
     */
    protected Map<String, Object> getRoot() {
        return new HashMap<>();
    }

    // 获取classpath
    // URL url = this.getClass().getResource("/");
    // System.out.println(url.getFile());
    @Test
    public void test() throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException,
            TemplateException {

        Map<String, Object> root = new HashMap<>();
        root.put("user", "jackie chen");
        Map<String, Object> latest = new HashMap<>();
        // 将它添加到根哈希表中
        root.put("latestProduct", latest);
        // 在latest中放置"url"和"name"
        latest.put("url", "products/greenmouse.html");
        latest.put("name", "green mouse");
        process(root, "test.ftl");

    }

    protected void process(Map<String, Object> root, String templateName) {
        try {
            Template template = cfg.getTemplate(templateName);
            // 将模板和模型合并
            Writer out = new OutputStreamWriter(System.out);
            template.process(root, out);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
