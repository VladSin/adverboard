package org.example.vladsin.adverboard.dao.converter;

import org.example.vladsin.adverboard.dao.entity.LocationEntity;
import org.example.vladsin.adverboard.model.Location;

public class LocationConverter {

    public static Location fromEntity(LocationEntity locationEntity) {
        if (locationEntity == null) {
            return null;
        }
        return new Location(
                locationEntity.getId(),
                locationEntity.getLocation()
        );
    }

    public static LocationEntity toEntity(Location location) {
        if (location == null) {
            return null;
        }
        final LocationEntity locationEntity = new LocationEntity();
        locationEntity.setId(location.getId());
        locationEntity.setLocation(location.getLocation());
        return locationEntity;
    }
}
