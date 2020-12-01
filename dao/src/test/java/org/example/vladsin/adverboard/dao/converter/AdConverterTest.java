package org.example.vladsin.adverboard.dao.converter;

import org.example.vladsin.adverboard.dao.entity.AdEntity;
import org.example.vladsin.adverboard.dao.entity.BillboardEntity;
import org.example.vladsin.adverboard.model.Ad;
import org.example.vladsin.adverboard.model.Billboard;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdConverterTest {

    @Test
    void fromEntity() {
        List<AdEntity> adEntities = new ArrayList<>();

        BillboardEntity billboardEntity = new BillboardEntity();
        billboardEntity.setBillboardId(null);
        billboardEntity.setLocation("location");
        billboardEntity.setPrice(20.2);
        billboardEntity.setUserId(1L);
        billboardEntity.setAds(adEntities);

        List<BillboardEntity> billboardEntities= new ArrayList<>();
        billboardEntities.add(billboardEntity);

        AdEntity adEntity = new AdEntity();
        adEntity.setAdId(null);
        adEntity.setLink("link");
        adEntity.setBillboards(billboardEntities);

        Ad ad = AdConverter.fromEntity(adEntity);
        assertNotNull(ad);
        assertEquals(ad.getId(), adEntity.getAdId());
        assertEquals(ad.getLink(), adEntity.getLink());
    }

    @Test
    void toEntity() {
        List<Ad> ads = new ArrayList<>();
        Billboard billboard = new Billboard(null, "location", 20.5, 1L, ads);
        List<Billboard> billboards= new ArrayList<>();
        billboards.add(billboard);

        Ad ad = new Ad(null, "link", billboards);
        AdEntity adEntity = AdConverter.toEntity(ad);
        assertNotNull(adEntity);
        assertEquals(ad.getLink(), adEntity.getLink());
    }
}