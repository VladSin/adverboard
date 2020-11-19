package org.example.vladsin.adverboard.dao.repository.impl;

import org.example.vladsin.adverboard.dao.entity.User;
import org.example.vladsin.adverboard.dao.repository.UserDao;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    public UserDaoImpl(){
        super();
        clazz = User.class;
    }
}