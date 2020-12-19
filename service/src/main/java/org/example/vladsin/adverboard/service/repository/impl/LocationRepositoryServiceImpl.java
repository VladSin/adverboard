package org.example.vladsin.adverboard.service.repository.impl;

import org.example.vladsin.adverboard.dao.repository.LocationRepositoryDao;
import org.example.vladsin.adverboard.model.Location;
import org.example.vladsin.adverboard.service.repository.LocationRepositoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LocationRepositoryServiceImpl implements LocationRepositoryService {

    private final LocationRepositoryDao locationRepositoryDao;

    public LocationRepositoryServiceImpl(LocationRepositoryDao locationRepositoryDao) {
        this.locationRepositoryDao = locationRepositoryDao;
    }

    @Override
    @Transactional
    public Location saveLocation(Location location) {
        return locationRepositoryDao.saveLocation(location);
    }

    @Override
    @Transactional
    public boolean updateLocation(Location location) {
        return locationRepositoryDao.updateLocation(location);
    }

    @Override
    @Transactional
    public boolean deleteLocation(long id) {
        return locationRepositoryDao.deleteLocation(id);
    }

    @Override
    @Transactional
    public Location getLocation(long id) {
        return locationRepositoryDao.getLocation(id);
    }

    @Override
    @Transactional
    public List<Location> getLocation() {
        return locationRepositoryDao.getLocation();
    }
}
