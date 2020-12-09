package org.example.vladsin.adverboard.service.repository.impl;

import org.example.vladsin.adverboard.dao.repository.GroupBillboardRepositoryDao;
import org.example.vladsin.adverboard.model.Billboard;
import org.example.vladsin.adverboard.model.GroupBillboards;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GroupBillboardServiceImplTest {

    @Mock
    GroupBillboardRepositoryDao dao;

    @InjectMocks
    GroupBillboardServiceImpl service;

    @Test
    void saveGroup() {
        when(dao.getGroupById(1L)).thenReturn(new GroupBillboards(1L, "group", 1L, new ArrayList<Billboard>()));
        final GroupBillboards groupFromDb = service.getGroupById(1);
        assertNotNull(groupFromDb);

        when(dao.saveGroup(groupFromDb)).thenReturn(groupFromDb);
        final GroupBillboards groupBillboards = service.saveGroup(groupFromDb);
        assertNotNull(groupBillboards);

        assertEquals(groupFromDb.getId(), groupBillboards.getId());
        assertEquals(groupFromDb.getGroupName(), groupBillboards.getGroupName());
        assertEquals(groupFromDb.getBillboards(), groupBillboards.getBillboards());
    }

    @Test
    void updateGroup() {
        when(dao.getGroupById(1L)).thenReturn(new GroupBillboards(1L, "group", 1L, new ArrayList<Billboard>()));
        final GroupBillboards groupFromDb = service.getGroupById(1);
        assertNotNull(groupFromDb);

        when(dao.updateGroup(groupFromDb)).thenReturn(true);
        final boolean update = service.updateGroup(groupFromDb);
        assertTrue(update);
    }

    @Test
    void deleteGroup() {
        when(dao.getGroupById(1L)).thenReturn(new GroupBillboards(1L, "group", 1L, new ArrayList<Billboard>()));
        final GroupBillboards groupFromDb = service.getGroupById(1);
        assertNotNull(groupFromDb);

        when(dao.deleteGroup(1L)).thenReturn(true);
        final boolean delete = service.deleteGroup(1L);
        assertTrue(delete);
    }

    @Test
    void getGroupById() {
        when(dao.getGroupById(1L)).thenReturn(new GroupBillboards(1L, "group", 1L, new ArrayList<Billboard>()));
        final GroupBillboards groupFromDb = service.getGroupById(1);

        assertNotNull(groupFromDb);
        assertEquals(groupFromDb.getGroupName(), "group");
    }

    @Test
    void getGroupByUserId() {
        when(dao.getGroupByUserId(1L)).thenReturn(new GroupBillboards(1L, "group", 1L, new ArrayList<Billboard>()));
        final GroupBillboards groupFromDb = service.getGroupByUserId(1);

        assertNotNull(groupFromDb);
        assertEquals(groupFromDb.getGroupName(), "group");
    }
}