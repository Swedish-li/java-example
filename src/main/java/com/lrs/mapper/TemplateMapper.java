package com.lrs.mapper;

import com.lrs.model.Template;
import org.apache.ibatis.annotations.Param;

public interface TemplateMapper {
    /**
     * 根据key值和语言获取模板信息
     *
     * @param key
     * @param lang
     * @return
     */
    Template queryByKey(@Param("key") String key, @Param("lang") String lang);
}
