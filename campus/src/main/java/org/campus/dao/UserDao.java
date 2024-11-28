package org.campus.dao;

import org.campus.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
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
		return user;
	}


	//通过电话获取用户信息
	@Override
	public User getUserByTelephone(String telephone){
		Query query = new Query(Criteria.where("telephone").is(telephone));
		return mongoTemplate.findOne(query, User.class);
	}


	//通过班级_id，一页十个进行分页
	@Override
	public Page<User> findUsersByClassId(String classId, Pageable pageable) {
		Query query = new Query();
		query.addCriteria(Criteria.where("cIno._id").is(classId));

		query.with(pageable);

		List<User> users = mongoTemplate.find(query, User.class);
		long total = mongoTemplate.count(query, User.class);

		return new PageImpl<>(users, pageable, total);
	}


	//通过_id查询用户
	@Override
	public User getUserById(String _id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(_id));
		return mongoTemplate.findOne(query, User.class);
	}


	//通过姓名查询相同名字的学生
	@Override
	public List<User> getStudentByName(String name) {
		Query query = new Query();
		query.addCriteria(Criteria.where("name").regex(name));
		return mongoTemplate.find(query, User.class);
	}


	// 通过_id修改status状态
	@Override
	public boolean editStatus(String id, int status) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		User user = mongoTemplate.findOne(query, User.class);

		if (user == null) {
			return false; // 用户不存在
		}

		user.setStatus(status);
		try {
			mongoTemplate.save(user);
			return true; // 更新成功
		} catch (Exception e) {
			return false; // 更新失败
		}
	}


	//通过电话号码查询mongodb，看其是否存在
	@Override
	public boolean isExist(String telephone) {
		Query query = new Query();
		query.addCriteria(Criteria.where("telephone").is(telephone));
		User user = mongoTemplate.findOne(query, User.class);
		return user != null;
	}
}

