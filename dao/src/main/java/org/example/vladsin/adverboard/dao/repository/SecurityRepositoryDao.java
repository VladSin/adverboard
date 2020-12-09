package org.example.vladsin.adverboard.dao.repository;

import org.example.vladsin.adverboard.model.AuthUser;

public interface SecurityRepositoryDao {

    AuthUser getByUserId(Long userId);

    AuthUser getByLogin(String login);
}
