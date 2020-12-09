package org.example.vladsin.adverboard.dao.repository.impl;

import org.example.vladsin.adverboard.dao.config.DaoConfig;
import org.example.vladsin.adverboard.dao.repository.LocationRepositoryDao;
import org.example.vladsin.adverboard.model.Location;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional
@Commit
class LocationRepositoryDaoImplTest {

    @Autowired
    private LocationRepositoryDao locationRepositoryDao;

    @Autowired
    SessionFactory sessionFactory;

    @Test
    void saveLocation() {
        final Location locationToSave = new Location(null, "location");
        final Location savedLocation = locationRepositoryDao.saveLocation(locationToSave);

        assertNotNull(savedLocation);
        assertEquals(locationToSave.getLocation(), savedLocation.getLocation());
    }

    @Test
    void updateLocation() {
        final Location locationToSave = new Location(null, "location");
        final Location savedLocation = locationRepositoryDao.saveLocation(locationToSave);
        final Long id = savedLocation.getId();
        sessionFactory.getCurrentSession().clear();

        final Location toUpdate = new Location(id, "location3");
        final boolean update = locationRepositoryDao.updateLocation(toUpdate);
        assertTrue(update);

        final Location afterUpdate = locationRepositoryDao.getLocation(id);

        assertEquals(toUpdate.getLocation(), afterUpdate.getLocation());
    }

    @Test
    void deleteLocation() {
        final Location locationToSave = new Location(null, "location");
        final Location savedLocation = locationRepositoryDao.saveLocation(locationToSave);
        final Long id = savedLocation.getId();
        sessionFactory.getCurrentSession().clear();

        final boolean deleted = locationRepositoryDao.deleteLocation(id);
        assertTrue(deleted);

        final Location afterDeleted = locationRepositoryDao.getLocation(id);
        assertNull(afterDeleted);
    }

    @Test
    void getLocation() {
        final Location locationToSave = new Location(null, "location");
        final Location savedLocation = locationRepositoryDao.saveLocation(locationToSave);
        final Long id = savedLocation.getId();

        final Location location = locationRepositoryDao.getLocation(id);
        assertNotNull(location);
        assertEquals(locationToSave.getLocation(), savedLocation.getLocation());
    }

    @Test
    void testGetLocation() {
        List<Location> locations = new ArrayList<>();
        locations.add(new Location(null, "location1"));
        locations.add(new Location(null, "location2"));

        List<Location> locationList = new ArrayList<>();
        for (Location l: locations) {
            locationList.add(locationRepositoryDao.saveLocation(l));
        }

        List<Location> getLocationList = locationRepositoryDao.getLocation();
        assertNotNull(getLocationList);
    }
}