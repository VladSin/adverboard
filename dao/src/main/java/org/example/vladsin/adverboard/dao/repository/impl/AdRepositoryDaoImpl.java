package org.example.vladsin.adverboard.dao.repository.impl;

import org.example.vladsin.adverboard.dao.converter.AdConverter;
import org.example.vladsin.adverboard.dao.converter.BillboardConverter;
import org.example.vladsin.adverboard.dao.entity.AdEntity;
import org.example.vladsin.adverboard.dao.entity.BillboardEntity;
import org.example.vladsin.adverboard.dao.repository.AdRepositoryDao;
import org.example.vladsin.adverboard.model.Ad;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AdRepositoryDaoImpl implements AdRepositoryDao {

    private static final Logger log = LoggerFactory.getLogger(AdRepositoryDaoImpl.class);
    private final SessionFactory factory;

    public AdRepositoryDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Ad saveAd(Ad ad) {
        AdEntity adEntity = AdConverter.toEntity(ad);
        final Session session = factory.getCurrentSession();
        session.save(adEntity);
        log.info("user saved:{}", ad);
        return AdConverter.fromEntity(adEntity);
    }

    @Override
    public boolean updateAd(Ad ad) {
        AdEntity adEntity = AdConverter.toEntity(ad);
        final Session session = factory.getCurrentSession();
        session.update(adEntity);
        log.info("user updated:{}", ad);
        return true;
    }

    @Override
    public boolean deleteAd(long id) {
        final Session session = factory.getCurrentSession();
        session.createQuery("delete from AdEntity as a where a.adId = :id")
                .setParameter("id", id)
                .executeUpdate();
        log.info("ad deleted by id:{}", id);
        return true;
    }

    @Override
    public Ad getAd(long id) {
        final Session session = factory.getCurrentSession();
        AdEntity adEntity = session.get(AdEntity.class, id);
        try {
            return AdConverter.fromEntity(adEntity);
        } catch (RuntimeException e){
            log.info("ad not found by id{}", id);
            return null;
        }
    }

    @Override
    public List<Ad> getAd() {
        final List<AdEntity> adEntities = factory.getCurrentSession().createQuery("from AdEntity ")
                .list();
        return adEntities.stream()
                .map(AdConverter::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Ad> getAdByBillboardId(long billboardId) {
        final Session session = factory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<AdEntity> criteria = cb.createQuery(AdEntity.class);
        Root<AdEntity> entityRoot = criteria.from(AdEntity.class);
        Predicate predicate = cb.and(
                cb.equal(entityRoot.get("billboardId"),  billboardId)
        );
        criteria.select(entityRoot).where(predicate);

        List<AdEntity> adEntities = session.createQuery(criteria).getResultList();
        return adEntities.stream()
                .map(AdConverter::fromEntity)
                .collect(Collectors.toList());
    }
}
