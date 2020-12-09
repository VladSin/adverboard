package org.example.vladsin.adverboard.service.repository.impl;

import org.example.vladsin.adverboard.dao.repository.LocationRepositoryDao;
import org.example.vladsin.adverboard.model.Location;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class LocationServiceImplTest {

    @Mock
    LocationRepositoryDao dao;

    @InjectMocks
    LocationServiceImpl service;

    @Test
    void saveLocation() {
        when(dao.getLocation(1L)).thenReturn(new Location(1L, "Loc1"));
        final Location locationFromDb = service.getLocation(1L);
        assertNotNull(locationFromDb);

        when(dao.saveLocation(locationFromDb)).thenReturn(locationFromDb);
        final Location location = service.saveLocation(locationFromDb);
        assertNotNull(location);

        assertEquals(locationFromDb.getLocation(), location.getLocation());
    }

    @Test
    void updateLocation() {
        when(dao.getLocation(1)).thenReturn(new Location(1L, "Loc1"));
        final Location locationFromDb = service.getLocation(1);
        assertNotNull(locationFromDb);

        when(dao.updateLocation(locationFromDb)).thenReturn(true);
        final boolean update = service.updateLocation(locationFromDb);
        assertTrue(update);
    }

    @Test
    void deleteLocation() {
        when(dao.getLocation(1)).thenReturn(new Location(1L, "Loc1"));
        final Location locationFromDb = service.getLocation(1);
        assertNotNull(locationFromDb);

        when(dao.deleteLocation(1L)).thenReturn(true);
        final boolean delete = service.deleteLocation(1L);
        assertTrue(delete);
    }

    @Test
    void getLocation() {
        when(dao.getLocation(1)).thenReturn(new Location(1L, "Loc1"));
        final Location locationFromDb = service.getLocation(1);
        assertNotNull(locationFromDb);

        final Location location = service.getLocation(1);
        assertNotNull(location);
        assertEquals(locationFromDb.getLocation(), location.getLocation());
    }

    @Test
    void testGetLocation() {
        List<Location> locations = new ArrayList<>();
        locations.add(new Location(1L, "Loc3"));
        locations.add(new Location(1L, "Loc4"));
        when(dao.getLocation()).thenReturn(locations);

        List<Location> locationsDao = service.getLocation();
        assertNotNull(locationsDao);
    }
}