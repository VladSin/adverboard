package org.example.vladsin.adverboard.dao.repository;

import org.example.vladsin.adverboard.model.User;

import java.util.List;

public interface UserRepositoryDao {

    User saveUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(long id);

    User getUser(long id);

    List<User> getUsers();
}