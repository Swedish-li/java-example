package com.lrs.freemarker.datebase;

import freemarker.cache.CacheStorage;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TestTempateLoader {

    private final static Version VERSION_2_3_23 = Configuration.VERSION_2_3_23;

    private final static Logger logger = LoggerFactory.getLogger(TestTempateLoader.class);
    private long lastModified = System.currentTimeMillis();

    @Test
    public void testTemplateLoader() throws IOException, TemplateException {

        Configuration config = new Configuration(VERSION_2_3_23);
        config.setTemplateLoader(new TemplateLoader() {
            Connection conn = null;

            @Override
            public Reader getReader(Object templateSource, String encoding) throws IOException {
                logger.info("getReader");
                Clob clob = (Clob) templateSource;
                try {
                    return clob.getCharacterStream();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public long getLastModified(Object templateSource) {
                logger.info("最后修改时间！");
                return lastModified;
            }

            @Override
            public Object findTemplateSource(String name) throws IOException {
                logger.info("获取模板源");
                PreparedStatement pstm = null;
                ResultSet rs = null;
                try {
                    if (conn == null || conn.isClosed()) {
                        Class.forName("com.mysql.jdbc.Driver");
                        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "root");
                        pstm = conn.prepareStatement("select template from t_template where status_key=? and lang=?");
                        logger.info("key:{}", name);
                        int index = name.indexOf('_');
                        String[] args = {name.substring(0, index), name.substring(index + 1)};
                        logger.info("参数1：{},参数2：{}", args[0], args[1]);
                        pstm.setString(1, args[0]);
                        pstm.setString(2, args[1]);
                        rs = pstm.executeQuery();
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } finally {

                }
                try {
                    rs.next();
                    return rs.getClob(1);
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);

                }

            }

            @Override
            public void closeTemplateSource(Object templateSource) throws IOException {

                try {
                    conn.close();
                    logger.info("关闭模板源");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

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
        System.out.println("========================华丽的分割线=================================");
        writer = new StringWriter();
        Template t2 = config.getTemplate(templateKey);

        t2.process(root, writer);
        System.out.println(writer.toString());

        writer.flush();
        System.out.println("========================华丽的分割线=================================");
        writer = new StringWriter();
        CacheStorage cache = config.getCacheStorage();
        // cache.remove(templateKey);
        // 手动清空缓存
        cache.clear();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // 修改最后更新时间
        lastModified = System.currentTimeMillis();
        Template t3 = config.getTemplate(templateKey);
        t3.process(root, writer);
        System.out.println(writer.toString());
    }
}
