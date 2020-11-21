package org.example.vladsin.adverboard.service.repository.impl;

import org.example.vladsin.adverboard.dao.repository.UserDao;
import org.example.vladsin.adverboard.model.User;
import org.example.vladsin.adverboard.service.repository.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        return userDao.saveUser(user);
    }

    @Override
    @Transactional
    public boolean deleteUser(long id) {
        return userDao.deleteUser(id);
    }

    @Override
    @Transactional
    public boolean updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    @Transactional
    public User getUser(long id) {
        return userDao.getUser(id);
    }

    @Override
    @Transactional
    public List<User> getUsers() {
        return userDao.getUsers();
    }
}