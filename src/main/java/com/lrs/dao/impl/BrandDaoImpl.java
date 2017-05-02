package com.lrs.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.lrs.dao.BrandDao;
import com.lrs.model.Brand;
import com.lrs.model.query.BrandQuery;

@Repository
public class BrandDaoImpl implements BrandDao {
	@Resource
	SqlSession sqlSession;

	@Override
	public List<Brand> selectBrandListByQuery(BrandQuery brandQuery) {

		return sqlSession.getMapper(BrandDao.class).selectBrandListByQuery(brandQuery);
	}

	@Override
	public void delete(Long[] ids) {
		sqlSession.getMapper(BrandDao.class).delete(ids);
	}

	@Override
	public Brand selectBrand(Long id) {
		return sqlSession.getMapper(BrandDao.class).selectBrand(id);
	}

}
