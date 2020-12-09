package org.example.vladsin.adverboard.service.repository.impl;

import org.example.vladsin.adverboard.dao.repository.UserRepositoryDao;
import org.example.vladsin.adverboard.model.User;
import org.example.vladsin.adverboard.service.repository.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepositoryDao userRepositoryDao;

    public UserServiceImpl(UserRepositoryDao userRepositoryDao) {
        this.userRepositoryDao = userRepositoryDao;
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        return userRepositoryDao.saveUser(user);
    }

    @Override
    @Transactional
    public boolean deleteUser(long id) {
        return userRepositoryDao.deleteUser(id);
    }

    @Override
    @Transactional
    public boolean updateUser(User user) {
        return userRepositoryDao.updateUser(user);
    }

    @Override
    @Transactional
    public User getUser(long id) {
        return userRepositoryDao.getUser(id);
    }

    @Override
    @Transactional
    public List<User> getUsers() {
        return userRepositoryDao.getUsers();
    }
}