package com.lrs.dao.impl;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.lrs.dao.PeopleDao;
import com.lrs.model.People;
@Repository
public class PeopleDaoImpl implements PeopleDao {
	//@Resource(name = "mysqlbSession")
	@Resource
	SqlSession sqlSession;

	@Override
	public void save(People people) {
		sqlSession.getMapper(PeopleDao.class).save(people);
	}

}
