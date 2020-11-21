package org.example.vladsin.adverboard.service.repository.impl;

import org.example.vladsin.adverboard.dao.entity.User;
import org.example.vladsin.adverboard.dao.repository.UserDao;
import org.example.vladsin.adverboard.service.repository.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao){
        super(userDao);
        this.userDao = userDao;
    }

    public User create(User user) {
        if(user != null){
            return userDao.add(user);
        }
        return null;
    }

    public void delete(User user) {
        if(user != null){
            userDao.delete(user.getId());
        }
    }
}