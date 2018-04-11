package com.lrs.freemarker.macro;

import com.lrs.freemarker.TestBase;
import org.junit.Test;

import java.util.Map;

public class TestMacro extends TestBase {
    private final static String functionFtlName = "structure.ftl";
    private final static String htmlTemlateFtl = "htmlTemplate.ftl";
    private String maroName = "test_marco.ftl";

    @Test
    public void testFirstMarco() {
        process(getRoot(), maroName);
    }

    /**
     * Freemark内建函数
     */
    @Test
    public void testFunction() {

        String[] args = {"str", "this", "img", "src"};
        Map<String, Object> root = getRoot();
        // html:将字符串中的所有特殊Html字符串使用实体来代替
        root.put("html", "<h1>this is a test html</h1>" + "\n @  ^ & ");
        // cap_first:将第一个字母转换为大写
        root.put("cap_first", "this cap first!");
        // 将大写字母转为小写
        root.put("lower_case", "THIS iS Lower Case");
        // 将字符串转换为大写
        root.put("upper_case", "this is uPper case");
        // 去除字符串首尾空格
        root.put("trim", "   tirm space     ");
        // 序列中元素个数
        root.put("size", args);
        // 数字的整数部分
        root.put("int", -12.5);

        root.put("userName", "Jack");

        root.put("cityName", "shanghai");

        root.put("countryName", "china");

        process(root, functionFtlName);
    }

    @Test
    public void testHtml() {
        process(getRoot(), htmlTemlateFtl);
    }
}
