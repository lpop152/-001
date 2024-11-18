package org.campus.dao;

import org.campus.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDao implements IUserDao{
	@Autowired
    MongoTemplate mongoTemplate;

	//用户登录
	@Override
	public User login(String telephone){
		Query query = new Query(Criteria.where("telephone").is(telephone));
		//查询
		User user=mongoTemplate.findOne(query, User.class);
		System.out.println(user);
//		long current=System.currentTimeMillis();

//		Criteria.where("start_time").lte(current).and("end_time").gte(current);

//	    query.fields().exclude("name");
		return user;
	}
}
