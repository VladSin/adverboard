package org.example.vladsin.adverboard.dao.repository;

import org.example.vladsin.adverboard.model.AuthUser;

public interface AuthUserRepositoryDao {

    AuthUser saveAuthUser(AuthUser authUser);

    boolean updateAuthUser(AuthUser authUser);

    boolean deleteAuthUser(long id);

    AuthUser getAuthUser(long id);
}
