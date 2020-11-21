package org.example.vladsin.adverboard.service.repository;

import org.example.vladsin.adverboard.model.AuthUser;

public interface AuthUserService {

    AuthUser saveAuthUser(AuthUser authUser);

    boolean updateAuthUser(AuthUser authUser);

    boolean deleteAuthUser(long id);

    AuthUser getAuthUser(long id);
}
