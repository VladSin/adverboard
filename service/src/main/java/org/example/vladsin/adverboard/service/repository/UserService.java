package org.example.vladsin.adverboard.service.repository;

import org.example.vladsin.adverboard.model.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    boolean updateUser(User user);
    boolean deleteUser(long id);

    User getUser(long id);

    List<User> getUsers();
}
