package org.example.vladsin.adverboard.dao.repository.impl;

import org.example.vladsin.adverboard.dao.converter.LocationConverter;
import org.example.vladsin.adverboard.dao.entity.LocationEntity;
import org.example.vladsin.adverboard.dao.repository.LocationRepositoryDao;
import org.example.vladsin.adverboard.model.Location;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;


@Repository
public class LocationRepositoryDaoImpl implements LocationRepositoryDao {

    private static final Logger log = LoggerFactory.getLogger(LocationRepositoryDaoImpl.class);
    private final SessionFactory factory;

    public LocationRepositoryDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Location saveLocation(Location location) {
        LocationEntity locationEntity = LocationConverter.toEntity(location);
        final Session session = factory.getCurrentSession();
        session.save(locationEntity);
        log.info("location saved:{}", location);
        return LocationConverter.fromEntity(locationEntity);
    }

    @Override
    public boolean updateLocation(Location location) {
        LocationEntity locationEntity = LocationConverter.toEntity(location);
        final Session session = factory.getCurrentSession();
        session.update(locationEntity);
        log.info("location updated:{}", location);
        return true;
    }

    @Override
    public boolean deleteLocation(long id) {
        final Session session = factory.getCurrentSession();
        session.createQuery("delete from LocationEntity as a where a.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        log.info("location deleted by id:{}", id);
        return true;
    }

    @Override
    public Location getLocation(long id) {
        final Session session = factory.getCurrentSession();
        LocationEntity locationEntity = session.get(LocationEntity.class, id);
        try {
            return LocationConverter.fromEntity(locationEntity);
        } catch (RuntimeException e) {
            log.info("location not found by id{}", id);
            return null;
        }
    }

    @Override
    public List<Location> getLocation() {
        final List<LocationEntity> locationEntities = factory.getCurrentSession().createQuery("from LocationEntity ")
                .list();
        return locationEntities.stream()
                .map(LocationConverter::fromEntity)
                .collect(Collectors.toList());
    }
}
