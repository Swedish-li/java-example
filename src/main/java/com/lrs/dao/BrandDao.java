package com.lrs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.lrs.model.Brand;
import com.lrs.model.query.BrandQuery;

public interface BrandDao {
	/**
	 * 
	 * @param brandQuery
	 * @return
	 */
	List<Brand> selectBrandListByQuery(BrandQuery brandQuery);

	/**
	 * 批量删除
	 * 
	 * @param ids
	 */
	void delete(Long[] ids);

	/**
	 * 使用注解
	 * 
	 * @param id
	 * @return
	 */

	@ConstructorArgs({ @Arg(column = "id", javaType = Long.class) })
	@Results({
		@Result(property="name",column="name",javaType=String.class),
		@Result(property="description",column="description",javaType=String.class),
		@Result(property="imgUrl",column="img_url",javaType=String.class),
		@Result(property="sort",column="sort",javaType=Integer.class),
		@Result(property="isDisplay",column="is_display",javaType=Integer.class)
	})
	@Select({
		"SELECT",
		"id,name,description,img_url,sort,is_display",
		"FROM brand WHERE id = #{id}"
	})
	Brand selectBrand(Long id);
}
