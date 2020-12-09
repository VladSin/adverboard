package org.example.vladsin.adverboard.dao.repository.impl;

import org.example.vladsin.adverboard.dao.config.DaoConfig;
import org.example.vladsin.adverboard.dao.repository.GroupBillboardRepositoryDao;
import org.example.vladsin.adverboard.model.Billboard;
import org.example.vladsin.adverboard.model.GroupBillboards;
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
class GroupBillboardRepositoryDaoImplTest {

    @Autowired
    private GroupBillboardRepositoryDao groupDao;

    @Autowired
    SessionFactory sessionFactory;

    @Test
    void saveGroup() {
        List<Billboard> billboards = new ArrayList<>();
        final GroupBillboards groupToSave = new GroupBillboards(null, "group1", 2L, billboards);
        final GroupBillboards savedGroup = groupDao.saveGroup(groupToSave);

        assertNotNull(savedGroup);
        assertEquals(groupToSave.getGroupName(), savedGroup.getGroupName());
        assertEquals(groupToSave.getUserId(), savedGroup.getUserId());
    }

    @Test
    void updateGroup() {
        List<Billboard> billboards = new ArrayList<>();
        final GroupBillboards groupToSave = new GroupBillboards(null, "group3", 3L, billboards);
        final GroupBillboards savedGroup = groupDao.saveGroup(groupToSave);
        final Long id = savedGroup.getId();
        sessionFactory.getCurrentSession().clear();

        final GroupBillboards toUpdate = new GroupBillboards(id, "group2", 2L, billboards);
        final boolean update = groupDao.updateGroup(toUpdate);
        assertTrue(update);

        final GroupBillboards afterUpdate = groupDao.getGroupById(id);

        assertEquals(toUpdate.getGroupName(), afterUpdate.getGroupName());
        assertEquals(toUpdate.getUserId(), afterUpdate.getUserId());
    }

    @Test
    void deleteGroup() {
        List<Billboard> billboards = new ArrayList<>();
        final GroupBillboards groupToSave = new GroupBillboards(null, "group3", 3L, billboards);
        final GroupBillboards savedGroup = groupDao.saveGroup(groupToSave);
        final Long id = savedGroup.getId();
        sessionFactory.getCurrentSession().clear();

        final boolean deleted = groupDao.deleteGroup(id);
        assertTrue(deleted);

        final GroupBillboards afterDeleted = groupDao.getGroupById(id);
        assertNull(afterDeleted);
    }

    @Test
    void getGroupById() {
        List<Billboard> billboards = new ArrayList<>();
        final GroupBillboards groupToSave = new GroupBillboards(null, "group4", 4L, billboards);
        final GroupBillboards savedGroup = groupDao.saveGroup(groupToSave);
        final Long id = savedGroup.getId();

        final GroupBillboards group = groupDao.getGroupById(id);

        assertNotNull(group);
        assertEquals(savedGroup.getUserId(), group.getUserId());
        assertEquals(savedGroup.getGroupName(), group.getGroupName());
        sessionFactory.getCurrentSession().clear();
    }

    @Test
    void getGroupByUserId() {
        List<Billboard> billboards = new ArrayList<>();
        final GroupBillboards groupToSave = new GroupBillboards(null, "group5", 5L, billboards);
        final GroupBillboards savedGroup = groupDao.saveGroup(groupToSave);
        final Long id = savedGroup.getUserId();

        final GroupBillboards group = groupDao.getGroupByUserId(id);

        assertNotNull(group);
        assertEquals(savedGroup.getUserId(), group.getUserId());
        assertEquals(savedGroup.getGroupName(), group.getGroupName());
        sessionFactory.getCurrentSession().clear();
    }
}