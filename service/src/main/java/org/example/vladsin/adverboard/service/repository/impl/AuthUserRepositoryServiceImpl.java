package org.example.vladsin.adverboard.service.repository.impl;

import org.example.vladsin.adverboard.dao.repository.AuthUserRepositoryDao;
import org.example.vladsin.adverboard.model.AuthUser;
import org.example.vladsin.adverboard.service.repository.AuthUserRepositoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthUserRepositoryServiceImpl implements AuthUserRepositoryService {

    private final AuthUserRepositoryDao authUserRepositoryDao;

    public AuthUserRepositoryServiceImpl(AuthUserRepositoryDao authUserRepositoryDao) {
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
