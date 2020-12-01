package org.example.vladsin.adverboard.service.repository.impl;

import org.example.vladsin.adverboard.dao.repository.AdDao;

import org.example.vladsin.adverboard.model.Ad;
import org.example.vladsin.adverboard.service.repository.AdService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdServiceImpl implements AdService {

    private final AdDao adDao;

    public AdServiceImpl(AdDao adDao) {
        this.adDao = adDao;
    }

    @Override
    @Transactional
    public Ad saveAd(Ad ad) {
        return adDao.saveAd(ad);
    }

    @Override
    @Transactional
    public boolean updateAd(Ad ad) {
        return adDao.updateAd(ad);
    }

    @Override
    @Transactional
    public boolean deleteAd(long id) {
        return adDao.deleteAd(id);
    }

    @Override
    @Transactional
    public Ad getAd(long id) {
        return adDao.getAd(id);
    }

    @Override
    @Transactional
    public List<Ad> getAd() {
        return adDao.getAd();
    }
}
