package org.example.vladsin.adverboard.dao.repository.impl;

import org.example.vladsin.adverboard.dao.converter.AdConverter;
import org.example.vladsin.adverboard.dao.entity.AdEntity;
import org.example.vladsin.adverboard.dao.repository.AdDao;
import org.example.vladsin.adverboard.model.Ad;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AdDaoImpl implements AdDao {

    private static final Logger log = LoggerFactory.getLogger(AdDaoImpl.class);
    private final SessionFactory factory;

    public AdDaoImpl(SessionFactory factory) {
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
}
