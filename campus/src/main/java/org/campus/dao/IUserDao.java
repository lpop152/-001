package org.campus.dao;

import org.springframework.stereotype.Repository;
import org.campus.pojo.User;


@Repository
public interface IUserDao {

	User login(String telephone);
}
