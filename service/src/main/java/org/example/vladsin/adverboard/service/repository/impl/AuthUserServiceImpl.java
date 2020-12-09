package org.example.vladsin.adverboard.service.repository.impl;

import org.example.vladsin.adverboard.dao.repository.AuthUserRepositoryDao;
import org.example.vladsin.adverboard.model.AuthUser;
import org.example.vladsin.adverboard.service.repository.AuthUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthUserServiceImpl implements AuthUserService {

    private final AuthUserRepositoryDao authUserRepositoryDao;

    public AuthUserServiceImpl(AuthUserRepositoryDao authUserRepositoryDao) {
        this.authUserRepositoryDao = authUserRepositoryDao;
    }

    @Override
    @Transactional
    public AuthUser saveAuthUser(AuthUser authUser) {
        return authUserRepositoryDao.saveAuthUser(authUser);
    }

    @Override
    @Transactional
    public boolean updateAuthUser(AuthUser authUser) {
        return authUserRepositoryDao.updateAuthUser(authUser);
    }

    @Override
    @Transactional
    public boolean deleteAuthUser(long id) {
        return authUserRepositoryDao.deleteAuthUser(id);
    }

    @Override
    @Transactional
    public AuthUser getAuthUser(long id) {
        return authUserRepositoryDao.getAuthUser(id);
    }
}
