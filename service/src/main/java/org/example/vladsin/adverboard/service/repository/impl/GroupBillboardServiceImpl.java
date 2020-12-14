package org.example.vladsin.adverboard.service.repository.impl;

import org.example.vladsin.adverboard.dao.repository.BillboardRepositoryDao;
import org.example.vladsin.adverboard.dao.repository.GroupBillboardRepositoryDao;
import org.example.vladsin.adverboard.model.Billboard;
import org.example.vladsin.adverboard.model.GroupBillboards;
import org.example.vladsin.adverboard.service.repository.GroupBillboardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GroupBillboardServiceImpl implements GroupBillboardService {

    private final GroupBillboardRepositoryDao groupBillboardRepositoryDao;
    private final BillboardRepositoryDao billboardRepositoryDao;

    public GroupBillboardServiceImpl(GroupBillboardRepositoryDao groupBillboardRepositoryDao, BillboardRepositoryDao billboardRepositoryDao) {
        this.groupBillboardRepositoryDao = groupBillboardRepositoryDao;
        this.billboardRepositoryDao = billboardRepositoryDao;
    }

    @Override
    @Transactional
    public GroupBillboards saveGroup(GroupBillboards group) {
        return groupBillboardRepositoryDao.saveGroup(group);
    }

    @Override
    @Transactional
    public boolean updateGroup(GroupBillboards group) {
        return groupBillboardRepositoryDao.updateGroup(group);
    }

    @Override
    @Transactional
    public boolean deleteGroup(long id) {
        return groupBillboardRepositoryDao.deleteGroup(id);
    }

    @Override
    @Transactional
    public GroupBillboards getGroupById(long id) {
        return groupBillboardRepositoryDao.getGroupById(id);
    }

    @Override
    @Transactional
    public GroupBillboards getGroupByUserId(long userId) {
        return groupBillboardRepositoryDao.getGroupByUserId(userId);
    }

    @Override
    @Transactional
    public List<GroupBillboards> getGroupsByUserId(long userId) {
        return groupBillboardRepositoryDao.getGroupsByUserId(userId);
    }

    @Override
    public List<Billboard> getBillboardsByGroupId(long groupId) {
        return billboardRepositoryDao.getBillboardsByGroupId(groupId);
    }
}
