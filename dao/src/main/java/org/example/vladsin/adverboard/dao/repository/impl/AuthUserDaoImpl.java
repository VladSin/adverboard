package org.example.vladsin.adverboard.dao.repository.impl;

import org.example.vladsin.adverboard.dao.converter.AuthUserConverter;
import org.example.vladsin.adverboard.dao.entity.AuthUserEntity;
import org.example.vladsin.adverboard.dao.repository.AuthUserDao;
import org.example.vladsin.adverboard.model.AuthUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class AuthUserDaoImpl  implements AuthUserDao {

    private static final Logger log = LoggerFactory.getLogger(AuthUserDaoImpl.class);
    private final SessionFactory factory;

    public AuthUserDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public AuthUser saveAuthUser(AuthUser authUser) {

        AuthUserEntity authUserEntity = AuthUserConverter.toEntity(authUser);
        final Session session = factory.getCurrentSession();
        session.save(authUserEntity);
        log.info("authUser saved:{}", authUser);
        return AuthUserConverter.fromEntity(authUserEntity);
    }

    @Override
    public boolean deleteAuthUser(long id) {
        final Session session = factory.getCurrentSession();
        session.createQuery("delete from AuthUserEntity as a where a.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        return true;
    }

    @Override
    public boolean updateAuthUser(AuthUser authUser) {
        AuthUserEntity authUserEntity = AuthUserConverter.toEntity(authUser);
        final Session session = factory.getCurrentSession();
        session.update(authUserEntity);
        return true;
    }

    @Override
    public AuthUser getAuthUser(long id) {
        final Session session = factory.getCurrentSession();
        AuthUserEntity authUserEntity = session.get(AuthUserEntity.class, id);
        try {
            AuthUser authUser = AuthUserConverter.fromEntity(authUserEntity);
            return authUser;
        } catch (RuntimeException e){
            log.info("authUser not found by id{}", id);
            return null;
        }
    }
}
