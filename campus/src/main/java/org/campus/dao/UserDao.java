package org.campus.dao;

import org.campus.jwt.JWTUtil;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Repository("userDao")
public class UserDao implements IUserDao{
	@Autowired
    MongoTemplate mongoTemplate;
	@Autowired
	private JWTUtil jwtUtil;

	//用户登录并更新token与登陆时间
	@Override
	public User login(String telephone){
		Query query = new Query(Criteria.where("telephone").is(telephone));
		//查询
		User user=mongoTemplate.findOne(query, User.class);
		System.out.println(user);
		// 获取当前时间并转换为字符串
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String currentTime = now.format(formatter);
		// 打印当前时间
		System.out.println("当前时间: " + currentTime);
		assert user != null;
		user.setLoginTime(currentTime);
		//通过用户电话生成一段token
		String token = jwtUtil.generateToken(user.getTelephone());
		user.setToken(token);

		System.out.println(user);

		//更新该用户数据
		mongoTemplate.save(user);

		return user;
	}


	//通过电话获取用户信息
	@Override
	public User getUserByTelephone(String telephone){
		Query query = new Query(Criteria.where("telephone").is(telephone));
		return mongoTemplate.findOne(query, User.class);
	}


	//通过班级id，一页十个进行分页
	@Override
	public Page<User> findUsersByClassId(Integer classId, int page, int size) {
		Query query = new Query();
		query.addCriteria(Criteria.where("cIno.id").is(classId));

		Pageable pageable = PageRequest.of(page, size);
		query.with(pageable);

		List<User> users = mongoTemplate.find(query, User.class);
		long total = mongoTemplate.count(query, User.class);

		return new PageImpl<>(users, pageable, total);
	}


	//通过id查询用户
	@Override
	public User getUserById(Integer id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		return mongoTemplate.findOne(query, User.class);
	}


	//通过姓名查询相同名字的学生
	@Override
	public List<User> getStudentByName(String name) {
		Query query = new Query();
		query.addCriteria(Criteria.where("name").regex(name));
		return mongoTemplate.find(query, User.class);
	}


	// 通过id修改status状态
	@Override
	public boolean editStatus(Integer id, Integer status) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
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
}

