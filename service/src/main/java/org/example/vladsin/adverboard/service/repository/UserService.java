package org.example.vladsin.adverboard.service.repository;

import org.example.vladsin.adverboard.model.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    User getUser(long id);

    boolean updateUser(User user);

    boolean deleteUser(long id);

    List<User> getUsers();
}
