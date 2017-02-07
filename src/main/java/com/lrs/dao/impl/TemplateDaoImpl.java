package com.lrs.dao.impl;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.lrs.dao.TemplateDao;
import com.lrs.model.Template;

@Repository
public class TemplateDaoImpl implements TemplateDao {
	@Resource
	SqlSession sqlSession;

	@Override
	public Template queryByKey(String key, String lang) {

		return sqlSession.getMapper(TemplateDao.class).queryByKey(key, lang);
	}

}
