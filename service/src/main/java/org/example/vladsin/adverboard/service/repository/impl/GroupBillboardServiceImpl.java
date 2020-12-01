package org.example.vladsin.adverboard.service.repository.impl;

import org.example.vladsin.adverboard.dao.repository.GroupBillboardDao;
import org.example.vladsin.adverboard.model.GroupBillboards;
import org.example.vladsin.adverboard.service.repository.GroupBillboardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GroupBillboardServiceImpl implements GroupBillboardService {

    private final GroupBillboardDao groupBillboardDao;

    public GroupBillboardServiceImpl(GroupBillboardDao groupBillboardDao) {
        this.groupBillboardDao = groupBillboardDao;
    }

    @Override
    @Transactional
    public GroupBillboards saveGroup(GroupBillboards group) {
        return groupBillboardDao.saveGroup(group);
    }

    @Override
    @Transactional
    public boolean updateGroup(GroupBillboards group) {
        return groupBillboardDao.updateGroup(group);
    }

    @Override
    @Transactional
    public boolean deleteGroup(long id) {
        return groupBillboardDao.deleteGroup(id);
    }

    @Override
    @Transactional
    public GroupBillboards getGroupById(long id) {
        return groupBillboardDao.getGroupById(id);
    }

    @Override
    @Transactional
    public GroupBillboards getGroupByUserId(long userId) {
        return groupBillboardDao.getGroupByUserId(userId);
    }

    @Override
    @Transactional
    public List<GroupBillboards> getGroupsByUserId(long userId) {
        return groupBillboardDao.getGroupsByUserId(userId);
    }
}
