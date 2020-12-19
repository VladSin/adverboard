package org.example.vladsin.adverboard.service.repository;

import org.example.vladsin.adverboard.model.Ad;

import java.util.List;

public interface AdRepositoryService {

    Ad saveAd(Ad ad);

    boolean updateAd(Ad ad);

    boolean deleteAd(long id);

    Ad getAd(long id);

    List<Ad> getAd();
}
