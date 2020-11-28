package org.example.vladsin.adverboard.dao.repository.impl;

import org.example.vladsin.adverboard.dao.converter.GroupBillboardsConverter;
import org.example.vladsin.adverboard.dao.entity.GroupBillboardsEntity;
import org.example.vladsin.adverboard.dao.repository.GroupBillboardDao;
import org.example.vladsin.adverboard.model.GroupBillboards;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class GroupBillboardDaoImpl implements GroupBillboardDao {

    private static final Logger log = LoggerFactory.getLogger(GroupBillboardDaoImpl.class);
    private final SessionFactory factory;

    public GroupBillboardDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public GroupBillboards saveGroup(GroupBillboards group) {
        GroupBillboardsEntity groupEntity = GroupBillboardsConverter.toEntity(group);
        final Session session = factory.getCurrentSession();
        session.save(groupEntity);
        log.info("billboard saved:{}", group);
        return GroupBillboardsConverter.fromEntity(groupEntity);
    }

    @Override
    public boolean updateGroup(GroupBillboards group) {
        GroupBillboardsEntity groupEntity = GroupBillboardsConverter.toEntity(group);
        final Session session = factory.getCurrentSession();
        session.update(groupEntity);
        log.info("group updated:{}", group);
        return true;
    }

    @Override
    public boolean deleteGroup(long id) {
        final Session session = factory.getCurrentSession();
        session.createQuery("delete from GroupBillboardsEntity as a where a.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        log.info("group deleted by id:{}", id);
        return true;
    }

    @Override
    public GroupBillboards getGroupById(long id) {
        GroupBillboardsEntity groupEntity;
        try {
            groupEntity = (GroupBillboardsEntity) factory.getCurrentSession()
                    .createQuery("from GroupBillboardsEntity g where g.id = :id")
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            log.info("group not found by id{}", id);
            groupEntity = null;
        }
        return GroupBillboardsConverter.fromEntity(groupEntity);
    }

    @Override
    public GroupBillboards getGroupByUserId(long userId) {
        GroupBillboardsEntity groupEntity;
        try {
            groupEntity = (GroupBillboardsEntity) factory.getCurrentSession()
                    .createQuery("from GroupBillboardsEntity g where g.userId = :userId")
                    .setParameter("userId", userId)
                    .getSingleResult();
        } catch (NoResultException e) {
            log.info("group not found by userId{}", userId);
            groupEntity = null;
        }
        return GroupBillboardsConverter.fromEntity(groupEntity);
    }
}
