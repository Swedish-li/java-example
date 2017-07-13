package com.lrs.mapper;

import org.apache.ibatis.annotations.Param;

import com.lrs.model.Template;

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
