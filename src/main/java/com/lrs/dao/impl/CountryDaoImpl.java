package com.lrs.dao.impl;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.lrs.dao.CountryDao;
import com.lrs.model.Country;

@Repository
public class CountryDaoImpl implements CountryDao {
	// @Resource(name = "mysqlaSession")
	@Resource
	SqlSession sqlSession;

	@Override
	public void save(Country country) {
		sqlSession.getMapper(CountryDao.class).save(country);
	}

}
