package org.example.vladsin.adverboard.dao.repository.impl;

import org.example.vladsin.adverboard.dao.config.DaoConfig;
import org.example.vladsin.adverboard.dao.repository.BillboardRepositoryDao;
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
class BillboardRepositoryDaoImplTest {

    @Autowired
    private BillboardRepositoryDao billboardRepositoryDao;

    @Autowired
    SessionFactory sessionFactory;

    @Test
    void saveBillboard() {
        List<Ad> ads = new ArrayList<>();
        final Billboard billboardToSave = new Billboard(null, "location",25.5, 1L, ads);
        final Billboard savedBillboard = billboardRepositoryDao.saveBillboard(billboardToSave);

        assertNotNull(savedBillboard);
        assertEquals(savedBillboard.getLocation(), billboardToSave.getLocation());
        assertEquals(savedBillboard.getPrice(), billboardToSave.getPrice());
        assertEquals(savedBillboard.getUserId(), billboardToSave.getUserId());
    }

    @Test
    void updateBillboard() {
        List<Ad> ads = new ArrayList<>();
        final Billboard billboardToSave = new Billboard(null, "location2",25.5, 1L, ads);
        final Billboard savedBillboard = billboardRepositoryDao.saveBillboard(billboardToSave);
        final Long id = savedBillboard.getId();
        sessionFactory.getCurrentSession().clear();

        final Billboard toUpdate = new Billboard(id, "location3",25.6, 2L, ads);
        final boolean update = billboardRepositoryDao.updateBillboard(toUpdate);
        assertTrue(update);

        final Billboard afterUpdate = billboardRepositoryDao.getBillboardById(id);

        assertEquals(toUpdate.getLocation(), afterUpdate.getLocation());
        assertEquals(toUpdate.getPrice(), afterUpdate.getPrice());
        assertEquals(toUpdate.getUserId(), afterUpdate.getUserId());
    }

    @Test
    void deleteBillboard() {
        List<Ad> ads = new ArrayList<>();
        final Billboard billboardToSave = new Billboard(null, "location",25.5, 1L, ads);
        final Billboard savedBillboard = billboardRepositoryDao.saveBillboard(billboardToSave);
        final Long id = savedBillboard.getId();
        sessionFactory.getCurrentSession().clear();

        final boolean deleted = billboardRepositoryDao.deleteBillboard(id);
        assertTrue(deleted);

        final Billboard afterDeleted = billboardRepositoryDao.getBillboardById(id);
        assertNull(afterDeleted);
    }

    @Test
    void getBillboardById() {
        List<Ad> ads = new ArrayList<>();
        final Billboard billboardToSave = new Billboard(null, "location",25.5, 1L, ads);
        final Billboard savedBillboard = billboardRepositoryDao.saveBillboard(billboardToSave);
        final Long id = savedBillboard.getId();

        final Billboard billboard = billboardRepositoryDao.getBillboardById(id);
        assertNotNull(billboard);
        assertEquals(savedBillboard.getLocation(), billboardToSave.getLocation());
        assertEquals(savedBillboard.getPrice(), billboardToSave.getPrice());
        assertEquals(savedBillboard.getUserId(), billboardToSave.getUserId());
        sessionFactory.getCurrentSession().clear();
    }

    @Test
    void getBillboardByLocation() {
        List<Ad> ads = new ArrayList<>();
        final Billboard billboardToSave = new Billboard(null, "location5",25.5, 1L, ads);
        final Billboard savedBillboard = billboardRepositoryDao.saveBillboard(billboardToSave);

        final Billboard billboard = billboardRepositoryDao.getBillboardByLocation(savedBillboard.getLocation());

        assertNotNull(billboard);
        assertEquals(savedBillboard.getLocation(), billboard.getLocation());
        assertEquals(savedBillboard.getPrice(), billboard.getPrice());
        assertEquals(savedBillboard.getUserId(), billboard.getUserId());
    }

    @Test
    void getBillboards() {
        List<Billboard> billboards = new ArrayList<>();
        List<Ad> ads = new ArrayList<>();
        billboards.add(new Billboard(null, "location10",10.5, 1L, ads));
        billboards.add(new Billboard(null, "location11",11.5, 2L, ads));

        List<Billboard> billboardList = new ArrayList<>();
        for (Billboard b: billboards) {
            billboardList.add(billboardRepositoryDao.saveBillboard(b));
        }

        List<Billboard> getAdList = billboardRepositoryDao.getBillboards();
        assertNotNull(getAdList);
        for (int i = 0; i < billboardList.size(); i++) {
            assertEquals(billboardList.get(i).getLocation(), billboards.get(i).getLocation());
            assertEquals(billboardList.get(i).getPrice(), billboards.get(i).getPrice());
            assertEquals(billboardList.get(i).getUserId(), billboards.get(i).getUserId());
        }
    }

    @Test
    void getBillboardsByUserId() {
        List<Billboard> billboards = new ArrayList<>();
        List<Ad> ads = new ArrayList<>();
        billboards.add(new Billboard(null, "location10",10.5, 1L, ads));
        billboards.add(new Billboard(null, "location11",11.5, 1L, ads));

        List<Billboard> billboardList = new ArrayList<>();
        for (Billboard b: billboards) {
            billboardList.add(billboardRepositoryDao.saveBillboard(b));
        }

        List<Billboard> getAdList = billboardRepositoryDao.getBillboardsByUserId(1L);
        assertNotNull(getAdList);
        for (int i = 0; i < billboardList.size(); i++) {
            assertEquals(billboardList.get(i).getLocation(), billboards.get(i).getLocation());
            assertEquals(billboardList.get(i).getPrice(), billboards.get(i).getPrice());
            assertEquals(billboardList.get(i).getUserId(), billboards.get(i).getUserId());
        }
    }
}