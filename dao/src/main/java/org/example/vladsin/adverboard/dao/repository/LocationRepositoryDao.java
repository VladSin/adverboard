package org.example.vladsin.adverboard.dao.repository;

import org.example.vladsin.adverboard.model.Location;

import java.util.List;

public interface LocationRepositoryDao {
    Location saveLocation(Location location);

    boolean updateLocation(Location location);

    boolean deleteLocation(long id);

    Location getLocation(long id);

    List<Location> getLocation();
}
