package com.lrs.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lrs.dao.CountryDao;
import com.lrs.dao.PeopleDao;
import com.lrs.model.Country;
import com.lrs.model.People;

@Service
public class TestService {
	@Resource
	private PeopleDao peopleDao;
	@Resource
	private CountryDao countryDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public void testTransaction() {
		Country test = new Country();
		test.setCountryname("测试国家");
		test.setCountrycode("12345");
		countryDao.save(test);

		People people = new People();
		people.setFirstName("jack");
		people.setLastName("li");
		people.setDob(new Date());
		people.setGender("m");
		// int i = 1 / 0;
		peopleDao.save(people);
	}

}
