package com.lrs.dao;

import org.apache.ibatis.annotations.Param;

import com.lrs.model.Template;

public interface TemplateDao {
	/**
	 * 根据key值和语言获取模板信息
	 * 
	 * @param key
	 * @param lang
	 * @return
	 */
	Template queryByKey(@Param("key") String key, @Param("lang") String lang);
}
