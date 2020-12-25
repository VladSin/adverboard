package org.example.vladsin.adverboard.dao.repository.impl;

import org.example.vladsin.adverboard.dao.config.DaoConfig;
import org.example.vladsin.adverboard.dao.repository.AdRepositoryDao;
import org.example.vladsin.adverboard.model.Ad;
import org.example.vladsin.adverboard.model.Billboard;
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
class AdRepositoryDaoImplTest {

    @Autowired
    private AdRepositoryDao adRepositoryDao;

    @Autowired
    SessionFactory sessionFactory;

    @Test
    void saveAd() {
        final Ad adToSave = new Ad(null, "link", 1L, "verified");
        final Ad savedAd = adRepositoryDao.saveAd(adToSave);

        assertNotNull(savedAd);
        assertEquals(adToSave.getLink(), savedAd.getLink());
    }

    @Test
    void updateAd() {
        final Ad adToSave = new Ad(null, "link2", 2L, "verified");
        final Ad savedAd = adRepositoryDao.saveAd(adToSave);
        final Long id = savedAd.getId();
        sessionFactory.getCurrentSession().clear();

        final Ad toUpdate = new Ad(id, "link3", 3L, "verified");
        final boolean update = adRepositoryDao.updateAd(toUpdate);
        assertTrue(update);

        final Ad afterUpdate = adRepositoryDao.getAd(id);
        assertEquals(toUpdate.getLink(), afterUpdate.getLink());
    }

    @Test
    void deleteAd() {
        final Ad adToSave = new Ad(null, "link", 4L, "verified");
        final Ad savedAd = adRepositoryDao.saveAd(adToSave);
        final Long id = savedAd.getId();
        sessionFactory.getCurrentSession().clear();

        final boolean deleted = adRepositoryDao.deleteAd(id);
        assertTrue(deleted);

        final Ad afterDeleted = adRepositoryDao.getAd(id);
        assertNull(afterDeleted);
    }

    @Test
    void getAd() {
        final Ad adToSave = new Ad(null, "link", 5L, "verified");
        final Ad savedAd = adRepositoryDao.saveAd(adToSave);
        final Long id = savedAd.getId();

        final Ad ad = adRepositoryDao.getAd(id);
        assertNotNull(ad);
        assertEquals(adToSave.getLink(), savedAd.getLink());
        sessionFactory.getCurrentSession().clear();
    }

    @Test
    void testGetAd() {
        List<Ad> ads = new ArrayList<>();
        ads.add(new Ad(null, "link1", 6L, "verified"));
        ads.add(new Ad(null, "link2", 7L, "verified"));

        List<Ad> adList = new ArrayList<>();
        for (Ad a: ads) {
            adList.add(adRepositoryDao.saveAd(a));
        }

        List<Ad> getAdList = adRepositoryDao.getAd();
        assertNotNull(getAdList);
        for (int i = 0; i < adList.size(); i++) {
            assertEquals(adList.get(i).getLink(), ads.get(i).getLink());
        }
    }
}