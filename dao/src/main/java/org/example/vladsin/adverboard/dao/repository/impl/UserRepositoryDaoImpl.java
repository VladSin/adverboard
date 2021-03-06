package org.example.vladsin.adverboard.dao.repository.impl;

import org.example.vladsin.adverboard.dao.converter.UserConverter;
import org.example.vladsin.adverboard.dao.entity.UserEntity;
import org.example.vladsin.adverboard.dao.repository.UserRepositoryDao;
import org.example.vladsin.adverboard.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryDaoImpl implements UserRepositoryDao {

    private static final Logger log = LoggerFactory.getLogger(UserRepositoryDaoImpl.class);
    private final SessionFactory factory;

    public UserRepositoryDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public User saveUser(User user) {
        UserEntity userEntity = UserConverter.toEntity(user);
        final Session session = factory.getCurrentSession();
        session.save(userEntity);
        log.info("user saved:{}", user);
        return UserConverter.fromEntity(userEntity);
    }

    @Override
    public boolean updateUser(User user) {
        UserEntity userEntity = UserConverter.toEntity(user);
        final Session session = factory.getCurrentSession();
        session.update(userEntity);
        log.info("user updated:{}", user);
        return true;
    }

    @Override
    public boolean deleteUser(long id) {
        final Session session = factory.getCurrentSession();
        session.createQuery("delete from UserEntity as a where a.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        log.info("user deleted:{}", id);
        return true;
    }

    @Override
    public User getUser(long id) {
        final Session session = factory.getCurrentSession();
        UserEntity userEntity = session.get(UserEntity.class, id);
        try {
            return UserConverter.fromEntity(userEntity);
        } catch (RuntimeException e){
            log.info("user not found by id{}", id);
            return null;
        }
    }

    @Override
    public List<User> getUsers() {
        final List<UserEntity> userEntities = factory.getCurrentSession().createQuery("from UserEntity ")
                .list();
        return userEntities.stream()
                .map(UserConverter::fromEntity)
                .collect(Collectors.toList());
    }
}