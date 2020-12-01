package org.example.vladsin.adverboard.service.repository.impl;

import org.example.vladsin.adverboard.dao.repository.BillboardDao;
import org.example.vladsin.adverboard.model.Billboard;
import org.example.vladsin.adverboard.service.repository.BillboardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BillboardServiceImpl implements BillboardService{

    private final BillboardDao billboardDao;

    public BillboardServiceImpl(BillboardDao billboardDao) {
        this.billboardDao = billboardDao;
    }

    @Override
    @Transactional
    public Billboard saveBillboard(Billboard billboard) {
        return billboardDao.saveBillboard(billboard);
    }

    @Override
    @Transactional
    public boolean updateBillboard(Billboard billboard) {
        return billboardDao.updateBillboard(billboard);
    }

    @Override
    @Transactional
    public boolean deleteBillboard(long id) {
        return billboardDao.deleteBillboard(id);
    }

    @Override
    @Transactional
    public Billboard getBillboardById(long id) {
        return billboardDao.getBillboardById(id);
    }

    @Override
    @Transactional
    public Billboard getBillboardByLocation(String location) {
        return billboardDao.getBillboardByLocation(location);
    }

    @Override
    @Transactional
    public List<Billboard> getBillboards() {
        return billboardDao.getBillboards();
    }

    @Override
    @Transactional
    public List<Billboard> getBillboardsByUserId(long userId) {
        return billboardDao.getBillboardsByUserId(userId);
    }


    @Override
    public List<Billboard> getListBillboardsByLocations(List<String> locations) {
        List<Billboard> billboards = new ArrayList<>();
        try{
            for (String loc: locations) {
                billboards.add(billboardDao.getBillboardByLocation(loc));
            }
        } catch (RuntimeException e){
            return null;
        }
        return billboards;
    }
}
