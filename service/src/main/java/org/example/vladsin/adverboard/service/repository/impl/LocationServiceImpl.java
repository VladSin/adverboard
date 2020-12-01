package org.example.vladsin.adverboard.service.repository.impl;

import org.example.vladsin.adverboard.dao.repository.LocationDao;
import org.example.vladsin.adverboard.model.Location;
import org.example.vladsin.adverboard.service.repository.LocationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {

    private final LocationDao locationDao;

    public LocationServiceImpl(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @Override
    @Transactional
    public Location saveLocation(Location location) {
        return locationDao.saveLocation(location);
    }

    @Override
    @Transactional
    public boolean updateLocation(Location location) {
        return locationDao.updateLocation(location);
    }

    @Override
    @Transactional
    public boolean deleteLocation(long id) {
        return locationDao.deleteLocation(id);
    }

    @Override
    @Transactional
    public Location getLocation(long id) {
        return locationDao.getLocation(id);
    }

    @Override
    @Transactional
    public List<Location> getLocation() {
        return locationDao.getLocation();
    }
}
