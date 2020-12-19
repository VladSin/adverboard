package org.example.vladsin.adverboard.service.repository.impl;

import org.example.vladsin.adverboard.dao.repository.AdRepositoryDao;

import org.example.vladsin.adverboard.model.Ad;
import org.example.vladsin.adverboard.service.repository.AdRepositoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdRepositoryServiceImpl implements AdRepositoryService {

    private final AdRepositoryDao adRepositoryDao;

    public AdRepositoryServiceImpl(AdRepositoryDao adRepositoryDao) {
        this.adRepositoryDao = adRepositoryDao;
    }

    @Override
    @Transactional
    public Ad saveAd(Ad ad) {
        return adRepositoryDao.saveAd(ad);
    }

    @Override
    @Transactional
    public boolean updateAd(Ad ad) {
        return adRepositoryDao.updateAd(ad);
    }

    @Override
    @Transactional
    public boolean deleteAd(long id) {
        return adRepositoryDao.deleteAd(id);
    }

    @Override
    @Transactional
    public Ad getAd(long id) {
        return adRepositoryDao.getAd(id);
    }

    @Override
    @Transactional
    public List<Ad> getAd() {
        return adRepositoryDao.getAd();
    }
}
