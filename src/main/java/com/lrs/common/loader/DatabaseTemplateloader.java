package com.lrs.common.loader;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.lrs.mapper.TemplateMapper;
import com.lrs.model.Template;

import freemarker.cache.TemplateLoader;

@Component
public class DatabaseTemplateloader implements TemplateLoader {
    @Resource
    TemplateMapper templateDao;

    private final static String DEFAULT_LANG = "zh_CN";

    @Override
    public Object findTemplateSource(String name) throws IOException {
        int index = name.indexOf('_');
        String lang = DEFAULT_LANG;
        String msg = name;
        if (index > 0) {
            lang = name.substring(index + 1);
            msg = name.substring(0, index);
        }

        return templateDao.queryByKey(msg, lang);
    }

    @Override
    public long getLastModified(Object templateSource) {
        return ((Template) templateSource).getLastModified().getTime();
        // return 1;
    }

    @Override
    public Reader getReader(Object templateSource, String encoding) throws IOException {
        return new StringReader(((Template) templateSource).getTemplate());
    }

    @Override
    public void closeTemplateSource(Object templateSource) throws IOException {
        // 在从数据库中获取模板数据时，此处可以什么也不做

    }

}
