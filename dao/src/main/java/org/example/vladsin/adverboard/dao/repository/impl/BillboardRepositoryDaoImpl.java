package org.example.vladsin.adverboard.dao.repository.impl;

import org.example.vladsin.adverboard.dao.converter.BillboardConverter;
import org.example.vladsin.adverboard.dao.entity.BillboardEntity;
import org.example.vladsin.adverboard.dao.repository.BillboardRepositoryDao;
import org.example.vladsin.adverboard.model.Billboard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BillboardRepositoryDaoImpl implements BillboardRepositoryDao {

    private static final Logger log = LoggerFactory.getLogger(BillboardRepositoryDaoImpl.class);
    private final SessionFactory factory;

    public BillboardRepositoryDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Billboard saveBillboard(Billboard billboard) {
        BillboardEntity billboardEntity = BillboardConverter.toEntity(billboard);
        final Session session = factory.getCurrentSession();
        session.save(billboardEntity);
        log.info("billboard saved:{}", billboard);
        return BillboardConverter.fromEntity(billboardEntity);
    }

    @Override
    public boolean updateBillboard(Billboard billboard) {
        BillboardEntity billboardEntity = BillboardConverter.toEntity(billboard);
        final Session session = factory.getCurrentSession();
        session.update(billboardEntity);
        log.info("billboard updated:{}", billboard);
        return true;
    }

    @Override
    public boolean deleteBillboard(long id) {
        final Session session = factory.getCurrentSession();
        session.createQuery("delete from BillboardEntity as a where a.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        log.info("billboard deleted by id:{}", id);
        return true;
    }

    @Override
    public Billboard getBillboardById(long id) {
        BillboardEntity billboardEntity;
        try {
            billboardEntity = (BillboardEntity) factory.getCurrentSession()
                    .createQuery("from BillboardEntity b where b.id = :id")
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            log.info("billboard not found by id{}", id);
            billboardEntity = null;
        }
        return BillboardConverter.fromEntity(billboardEntity);
    }

    @Override
    public Billboard getBillboardByLocation(String location) {
        BillboardEntity billboardEntity;
        try {
            billboardEntity = (BillboardEntity) factory.getCurrentSession()
                    .createQuery("from BillboardEntity b where b.location = :location")
                    .setParameter("location", location)
                    .getSingleResult();
        } catch (NoResultException e) {
            log.info("billboard not found by location{}", location);
            billboardEntity = null;
        }
        return BillboardConverter.fromEntity(billboardEntity);
    }

    @Override
    public List<Billboard> getBillboards() {
        final List<BillboardEntity> billboardEntities = factory.getCurrentSession().createQuery("from BillboardEntity ")
                .list();
        return billboardEntities.stream()
                .map(BillboardConverter::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Billboard> getBillboardsByUserId(long userId) {
        final Session session = factory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<BillboardEntity> criteria = cb.createQuery(BillboardEntity.class);
        Root<BillboardEntity> entityRoot = criteria.from(BillboardEntity.class);
        Predicate predicate = cb.and(
                cb.equal(entityRoot.get("userId"),  userId)
        );
        criteria.select(entityRoot).where(predicate);

        List<BillboardEntity> billboardEntities = session.createQuery(criteria).getResultList();
        return billboardEntities.stream()
                .map(BillboardConverter::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Billboard> getBillboardsByGroupId(long groupId) {
        final Session session = factory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<BillboardEntity> criteria = cb.createQuery(BillboardEntity.class);
        Root<BillboardEntity> entityRoot = criteria.from(BillboardEntity.class);
        Predicate predicate = cb.and(
                cb.equal(entityRoot.get("groupId"),  groupId)
        );
        criteria.select(entityRoot).where(predicate);

        List<BillboardEntity> billboardEntities = session.createQuery(criteria).getResultList();
        return billboardEntities.stream()
                .map(BillboardConverter::fromEntity)
                .collect(Collectors.toList());
    }
}
