package org.campus.dao;

import java.util.Map;

import javax.annotation.Resource;

import org.campus.controller.UserController;
import org.campus.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;



@Repository
public class UserDao implements IUserDao{
	private static final Logger log = LoggerFactory.getLogger(UserDao.class);
	@Autowired
    MongoTemplate mongoTemplate;
	public User login(String telephone){
		Query query = new Query(Criteria.where("telephone").is(telephone));
		long current=System.currentTimeMillis();
		
		Criteria.where("start_time").lte(current).and("end_time").gte(current);
		
	   query.fields().exclude("name");
		
		User user=mongoTemplate.findOne(query, User.class);
		
	    
		
		log.info(user.toString());
		return user;
	}
}
