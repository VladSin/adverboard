package org.example.vladsin.adverboard.service.repository;

import org.example.vladsin.adverboard.dao.entity.User;

public interface UserService extends BaseService<User>{

    User create(User user);

    void delete(User user);
}
