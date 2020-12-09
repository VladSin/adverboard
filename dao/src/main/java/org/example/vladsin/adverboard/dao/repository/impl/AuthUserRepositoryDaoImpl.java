package org.example.vladsin.adverboard.dao.repository.impl;

import org.example.vladsin.adverboard.dao.converter.AuthUserConverter;
import org.example.vladsin.adverboard.dao.entity.AuthUserEntity;
import org.example.vladsin.adverboard.dao.repository.AuthUserRepositoryDao;
import org.example.vladsin.adverboard.dao.repository.SecurityRepositoryDao;
import org.example.vladsin.adverboard.model.AuthUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class AuthUserRepositoryDaoImpl implements AuthUserRepositoryDao, SecurityRepositoryDao {

    private static final Logger log = LoggerFactory.getLogger(AuthUserRepositoryDaoImpl.class);
    private final SessionFactory factory;

    public AuthUserRepositoryDaoImpl(SessionFactory factory) {
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
        log.info("authUser deleted by id:{}", id);
        return true;
    }

    @Override
    public boolean updateAuthUser(AuthUser authUser) {
        AuthUserEntity authUserEntity = AuthUserConverter.toEntity(authUser);
        final Session session = factory.getCurrentSession();
        session.update(authUserEntity);
        log.info("authUser updated:{}", authUser);
        return true;
    }

    @Override
    public AuthUser getAuthUser(long id) {
        final Session session = factory.getCurrentSession();
        AuthUserEntity authUserEntity = session.get(AuthUserEntity.class, id);
        try {
            return AuthUserConverter.fromEntity(authUserEntity);
        } catch (RuntimeException e){
            log.info("authUser not found by id{}", id);
            return null;
        }
    }

    @Override
    public AuthUser getByUserId(Long userId) {
        AuthUserEntity authUser;
        try {
            authUser = (AuthUserEntity) factory.getCurrentSession()
                    .createQuery("from AuthUserEntity au where au.userId = :userId")
                    .setParameter("userId", userId)
                    .getSingleResult();
        } catch (NoResultException e) {
            log.info("user not found by userId{}", userId);
            authUser = null;
        }
        return AuthUserConverter.fromEntity(authUser);
    }

    @Override
    public AuthUser getByLogin(String login) {
        AuthUserEntity authUser;
        try {
            authUser = (AuthUserEntity) factory.getCurrentSession()
                    .createQuery("from AuthUserEntity au where au.login = :login")
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException e) {
            log.info("user not found by login{}", login);
            authUser = null;
        }
        return AuthUserConverter.fromEntity(authUser);
    }
}
