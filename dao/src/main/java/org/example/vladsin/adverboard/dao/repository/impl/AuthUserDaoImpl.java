package org.example.vladsin.adverboard.dao.repository.impl;

import org.example.vladsin.adverboard.dao.entity.AuthUser;
import org.example.vladsin.adverboard.dao.repository.AuthUserDao;
import org.springframework.stereotype.Repository;

@Repository
public class AuthUserDaoImpl extends BaseDaoImpl<AuthUser> implements AuthUserDao {

    public AuthUserDaoImpl() {
        super();
        clazz = AuthUser.class;
    }
}
