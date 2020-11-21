package org.example.vladsin.adverboard.service.repository.impl;

import org.example.vladsin.adverboard.dao.entity.AuthUser;
import org.example.vladsin.adverboard.dao.repository.AuthUserDao;
import org.example.vladsin.adverboard.service.repository.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthUserServiceImpl extends BaseServiceImpl<AuthUser> implements AuthUserService {

    private final AuthUserDao authUserDao;

    @Autowired
    public AuthUserServiceImpl(AuthUserDao authUserDao){
        super(authUserDao);
        this.authUserDao = authUserDao;
    }

    public AuthUser create(AuthUser authUser) {
        if(authUser != null){
            return authUserDao.add(authUser);
        }
        return null;
    }

    public void delete(AuthUser authUser) {
        if(authUser != null){
            authUserDao.delete(authUser.getId());
        }
    }
}
