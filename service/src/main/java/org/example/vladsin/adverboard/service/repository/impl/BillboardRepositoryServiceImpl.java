package org.example.vladsin.adverboard.service.repository.impl;

import org.example.vladsin.adverboard.dao.repository.BillboardRepositoryDao;
import org.example.vladsin.adverboard.model.Billboard;
import org.example.vladsin.adverboard.service.repository.BillboardRepositoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BillboardRepositoryServiceImpl implements BillboardRepositoryService {

    private final BillboardRepositoryDao billboardRepositoryDao;

    public BillboardRepositoryServiceImpl(BillboardRepositoryDao billboardRepositoryDao) {
        this.billboardRepositoryDao = billboardRepositoryDao;
    }

    @Override
    @Transactional
    public Billboard saveBillboard(Billboard billboard) {
        return billboardRepositoryDao.saveBillboard(billboard);
    }

    @Override
    @Transactional
    public boolean updateBillboard(Billboard billboard) {
        return billboardRepositoryDao.updateBillboard(billboard);
    }

    @Override
    @Transactional
    public boolean deleteBillboard(long id) {
        return billboardRepositoryDao.deleteBillboard(id);
    }

    @Override
    @Transactional
    public Billboard getBillboardById(long id) {
        return billboardRepositoryDao.getBillboardById(id);
    }

    @Override
    @Transactional
    public Billboard getBillboardByLocation(String location) {
        return billboardRepositoryDao.getBillboardByLocation(location);
    }

    @Override
    @Transactional
    public List<Billboard> getBillboards() {
        return billboardRepositoryDao.getBillboards();
    }

    @Override
    @Transactional
    public List<Billboard> getBillboardsByUserId(long userId) {
        return billboardRepositoryDao.getBillboardsByUserId(userId);
    }

    @Override
    @Transactional
    public List<Billboard> getBillboardsByGroupId(long groupId) {
        return billboardRepositoryDao.getBillboardsByGroupId(groupId);
    }

    @Override
    @Transactional
    public List<Billboard> getListBillboardsByLocations(List<String> locations) {
        List<Billboard> billboards = new ArrayList<>();
        try{
            for (String loc: locations) {
                billboards.add(billboardRepositoryDao.getBillboardByLocation(loc));
            }
        } catch (RuntimeException e){
            return null;
        }
        return billboards;
    }
}
