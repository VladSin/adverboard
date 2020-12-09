package org.example.vladsin.adverboard.service.repository.impl;

import org.example.vladsin.adverboard.dao.repository.AdRepositoryDao;
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
class AdServiceImplTest {

    @Mock
    AdRepositoryDao dao;

    @InjectMocks
    AdServiceImpl service;

    @Test
    void saveAd() {
        when(dao.getAd(1)).thenReturn(new Ad(1L, "Link1", new ArrayList<Billboard>()));
        final Ad adFromDb = service.getAd(1);
        assertNotNull(adFromDb);

        when(dao.saveAd(adFromDb)).thenReturn(adFromDb);
        final Ad ad = service.saveAd(adFromDb);
        assertNotNull(ad);

        assertEquals(adFromDb.getId(), ad.getId());
        assertEquals(adFromDb.getLink(), ad.getLink());
        assertEquals(adFromDb.getBillboards(), ad.getBillboards());
    }

    @Test
    void updateAd() {
        when(dao.getAd(1)).thenReturn(new Ad(1L, "Link1", new ArrayList<Billboard>()));
        final Ad adFromDb = service.getAd(1);
        assertNotNull(adFromDb);

        when(dao.updateAd(adFromDb)).thenReturn(true);
        final boolean update = service.updateAd(adFromDb);
        assertTrue(update);
    }

    @Test
    void deleteAd() {
        when(dao.getAd(1)).thenReturn(new Ad(1L, "Link1", new ArrayList<Billboard>()));
        final Ad adFromDb = service.getAd(1);
        assertNotNull(adFromDb);

        when(dao.deleteAd(1L)).thenReturn(true);
        final boolean delete = service.deleteAd(1L);
        assertTrue(delete);
    }

    @Test
    void getAd() {
        when(dao.getAd(1)).thenReturn(new Ad(1L, "Link1", new ArrayList<Billboard>()));
        final Ad adFromDb = service.getAd(1);
        assertNotNull(adFromDb);

        final Ad ad = service.getAd(1);
        assertNotNull(ad);
        assertEquals(adFromDb.getId(), ad.getId());
        assertEquals(adFromDb.getLink(), ad.getLink());
        assertEquals(adFromDb.getBillboards(), ad.getBillboards());
    }

    @Test
    void testGetAd() {
        List<Ad> ads = new ArrayList<>();
        ads.add(new Ad(1L, "Link1", new ArrayList<Billboard>()));
        ads.add(new Ad(1L, "Link1", new ArrayList<Billboard>()));
        when(dao.getAd()).thenReturn(ads);

        List<Ad> adDao = service.getAd();
        assertNotNull(adDao);
        for (int i = 0; i < adDao.size(); i++) {
            assertEquals(adDao.get(i).getId(), ads.get(i).getId());
            assertEquals(adDao.get(i).getLink(), ads.get(i).getLink());
            assertEquals(adDao.get(i).getBillboards(), ads.get(i).getBillboards());
        }
    }
}