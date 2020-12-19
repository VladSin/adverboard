package org.example.vladsin.adverboard.service.repository.impl;

import org.example.vladsin.adverboard.dao.repository.BillboardRepositoryDao;
import org.example.vladsin.adverboard.model.Ad;
import org.example.vladsin.adverboard.model.Billboard;
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
class BillboardRepositoryServiceImplTest {

    @Mock
    BillboardRepositoryDao dao;

    @InjectMocks
    BillboardRepositoryServiceImpl service;

    @Test
    void saveBillboard() {
        when(dao.getBillboardById(1L)).thenReturn(new Billboard(1L, "Location", 20.0, 1L, new ArrayList<Ad>()));
        final Billboard billboardFromDb = service.getBillboardById(1L);
        assertNotNull(billboardFromDb);

        when(dao.saveBillboard(billboardFromDb)).thenReturn(billboardFromDb);
        final Billboard billboard = service.saveBillboard(billboardFromDb);
        assertNotNull(billboard);

        assertEquals(billboardFromDb.getId(), billboard.getId());
        assertEquals(billboardFromDb.getUserId(), billboard.getUserId());
        assertEquals(billboardFromDb.getPrice(), billboard.getPrice());
        assertEquals(billboardFromDb.getLocation(), billboard.getLocation());
    }

    @Test
    void updateBillboard() {
        when(dao.getBillboardById(1L)).thenReturn(new Billboard(1L, "Location", 20.0, 1L, new ArrayList<Ad>()));
        final Billboard billboardFromDb = service.getBillboardById(1L);
        assertNotNull(billboardFromDb);

        when(dao.updateBillboard(billboardFromDb)).thenReturn(true);
        final boolean update = service.updateBillboard(billboardFromDb);
        assertTrue(update);
    }

    @Test
    void deleteBillboard() {
        when(dao.getBillboardById(1L)).thenReturn(new Billboard(1L, "Location", 20.0, 1L, new ArrayList<Ad>()));
        final Billboard billboardFromDb = service.getBillboardById(1L);
        assertNotNull(billboardFromDb);

        when(dao.deleteBillboard(1L)).thenReturn(true);
        final boolean delete = service.deleteBillboard(1L);
        assertTrue(delete);
    }

    @Test
    void getBillboardById() {
        when(dao.getBillboardById(1L)).thenReturn(new Billboard(1L, "Location", 20.0, 1L, new ArrayList<Ad>()));
        final Billboard billboardFromDb = service.getBillboardById(1L);
        assertNotNull(billboardFromDb);

        final Billboard billboard = service.getBillboardById(1);
        assertNotNull(billboard);

        assertEquals(billboardFromDb.getId(), billboard.getId());
        assertEquals(billboardFromDb.getUserId(), billboard.getUserId());
        assertEquals(billboardFromDb.getPrice(), billboard.getPrice());
        assertEquals(billboardFromDb.getLocation(), billboard.getLocation());
    }

    @Test
    void getBillboardByLocation() {
        when(dao.getBillboardByLocation("Location")).thenReturn(new Billboard(1L, "Location", 20.0, 1L, new ArrayList<Ad>()));
        final Billboard billboardFromDb = service.getBillboardByLocation("Location");
        assertNotNull(billboardFromDb);

        final Billboard billboard = service.getBillboardByLocation("Location");
        assertNotNull(billboard);

        assertEquals(billboardFromDb.getId(), billboard.getId());
        assertEquals(billboardFromDb.getUserId(), billboard.getUserId());
        assertEquals(billboardFromDb.getPrice(), billboard.getPrice());
        assertEquals(billboardFromDb.getLocation(), billboard.getLocation());
    }

    @Test
    void getBillboards() {
        List<Billboard> billboards = new ArrayList<>();
        billboards.add(new Billboard(1L, "Location1", 20.0, 1L, new ArrayList<Ad>()));
        billboards.add(new Billboard(2L, "Location2", 30.0, 2L, new ArrayList<Ad>()));
        when(dao.getBillboards()).thenReturn(billboards);

        List<Billboard> billboardList = dao.getBillboards();
        assertNotNull(billboardList);
        for (int i = 0; i < billboardList.size(); i++) {
            assertEquals(billboardList.get(i).getId(), billboards.get(i).getId());
            assertEquals(billboardList.get(i).getLocation(), billboards.get(i).getLocation());
            assertEquals(billboardList.get(i).getPrice(), billboards.get(i).getPrice());
            assertEquals(billboardList.get(i).getUserId(), billboards.get(i).getUserId());
        }
    }

    @Test
    void getBillboardsByUserId() {
        List<Billboard> billboards = new ArrayList<>();
        billboards.add(new Billboard(1L, "Location1", 20.0, 1L, new ArrayList<Ad>()));
        billboards.add(new Billboard(2L, "Location2", 30.0, 1L, new ArrayList<Ad>()));
        when(dao.getBillboardsByUserId(1L)).thenReturn(billboards);

        List<Billboard> billboardList = service.getBillboardsByUserId(1L);
        assertNotNull(billboardList);
        for (int i = 0; i < billboardList.size(); i++) {
            assertEquals(billboardList.get(i).getId(), billboards.get(i).getId());
            assertEquals(billboardList.get(i).getLocation(), billboards.get(i).getLocation());
            assertEquals(billboardList.get(i).getPrice(), billboards.get(i).getPrice());
            assertEquals(billboardList.get(i).getUserId(), billboards.get(i).getUserId());
        }
    }
}