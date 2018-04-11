package com.lrs.controller;

import com.lrs.model.Brand;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("view")
public class PageController {

    private final static Logger LOG = LoggerFactory.getLogger(PageController.class);
    // 使用代理方式生成工厂类
    // org.springframework.beans.factory.support.AutowireUtils$ObjectFactoryDelegatingInvocationHandler
    @Autowired
    private HttpServletRequest request;

    @RequestMapping("request-prop")
    public ResponseEntity<String> testRequestProp() {
        LOG.info(ToStringBuilder.reflectionToString(request));
        LOG.info("hash code:{}", request.hashCode());
        return ResponseEntity.ok(request.toString());
    }

    /**
     * 数据类型转换注入
     * <p>
     * 当类型转换出错时Spring会抛出以下异常
     * <p>
     * org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
     * <p>
     * java.lang.NumberFormatException
     * <p>
     * 当抛出次异常时Tomcat的响应状态
     * <p>
     * HTTP Status 400
     * <p>
     * StringHttpMessageConverter内部使用ISO-8859-1
     *
     * @param start
     */
    @RequestMapping(value = "convert-int")
    public String toInt(int start) {
        return "convert to test 上海!";
    }

    /**
     * Json响应
     *
     * @return
     */
    @RequestMapping(value = "get-brand")
    public Brand getBrand() {
        Brand brand = new Brand()
                .setId(5L)
                .setName("test")
                .setDescription("test desc")
                .setImgUrl("imgUrl")
                .setIsDisplay(1)
                .setSort(10);

        return brand;
    }

    @RequestMapping("get-map")
    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("decimal", String.valueOf(new BigDecimal(1.57788).doubleValue()));
        map.put("date", new Date());
        map.put("double", 1.879);
        return map;
    }

    /**
     * 用于测试服务器的 URL转码是否配置好
     *
     * @param str
     * @return
     */
    @RequestMapping(value = "param-str", method = RequestMethod.GET)
    public String getStringParam(String str, @RequestHeader("Accept-Language") String lang,
                                 @CookieValue("b3log-latke") String user) {
        LOG.info("Language:{}", lang);
        LOG.info("User:{}", user);
        return str;
    }
}
