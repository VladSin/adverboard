package org.example.vladsin.adverboard.dao.converter;

import org.example.vladsin.adverboard.dao.entity.LocationEntity;
import org.example.vladsin.adverboard.model.Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationConverterTest {

    @Test
    void fromEntity() {
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setId(null);
        locationEntity.setLocation("location");

        Location location = LocationConverter.fromEntity(locationEntity);
        assertNotNull(location);
        assertEquals(location.getLocation(), locationEntity.getLocation());
    }

    @Test
    void toEntity() {
        Location location = new Location(null, "location");
        LocationEntity locationEntity = LocationConverter.toEntity(location);
        assertNotNull(locationEntity);
        assertEquals(location.getLocation(), locationEntity.getLocation());
    }
}