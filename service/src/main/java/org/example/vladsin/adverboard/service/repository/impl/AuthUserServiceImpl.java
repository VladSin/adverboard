package org.example.vladsin.adverboard.service.repository.impl;

import org.example.vladsin.adverboard.dao.repository.AuthUserDao;
import org.example.vladsin.adverboard.model.AuthUser;
import org.example.vladsin.adverboard.service.repository.AuthUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthUserServiceImpl implements AuthUserService {

    private final AuthUserDao authUserDao;

    public AuthUserServiceImpl(AuthUserDao authUserDao) {
        this.authUserDao = authUserDao;
    }

    @Override
    @Transactional
    public AuthUser saveAuthUser(AuthUser authUser) {
        return authUserDao.saveAuthUser(authUser);
    }

    @Override
    @Transactional
    public boolean updateAuthUser(AuthUser authUser) {
        return authUserDao.updateAuthUser(authUser);
    }

    @Override
    @Transactional
    public boolean deleteAuthUser(long id) {
        return authUserDao.deleteAuthUser(id);
    }

    @Override
    @Transactional
    public AuthUser getAuthUser(long id) {
        return authUserDao.getAuthUser(id);
    }
}
