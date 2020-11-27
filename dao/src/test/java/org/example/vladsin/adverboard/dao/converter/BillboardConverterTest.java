package org.example.vladsin.adverboard.dao.converter;

import org.example.vladsin.adverboard.dao.entity.AdEntity;
import org.example.vladsin.adverboard.dao.entity.BillboardEntity;
import org.example.vladsin.adverboard.model.Ad;
import org.example.vladsin.adverboard.model.Billboard;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BillboardConverterTest {

    @Test
    void fromEntity() {
        List<BillboardEntity> billboardEntities = new ArrayList<>();
        AdEntity adEntity = new AdEntity(null, "link", billboardEntities);

        List<AdEntity> adEntities= new ArrayList<>();
        adEntities.add(adEntity);

        BillboardEntity billboardEntity = new BillboardEntity();
        billboardEntity.setBillboardId(null);
        billboardEntity.setLocation("location");
        billboardEntity.setPrice(20.5);
        billboardEntity.setUserId(1L);
        billboardEntity.setAds(adEntities);

        Billboard billboard = BillboardConverter.fromEntity(billboardEntity);
        assertNotNull(billboard);
        assertEquals(billboard.getLocation(), billboardEntity.getLocation());
        assertEquals(billboard.getPrice(), billboardEntity.getPrice());
        assertEquals(billboard.getUserId(), billboardEntity.getUserId());
    }

    @Test
    void toEntity() {
        List<Billboard> billboards = new ArrayList<>();
        Ad ad = new Ad(null, "link", billboards);
        List<Ad> ads= new ArrayList<>();
        ads.add(ad);

        Billboard billboard = new Billboard(null, "location", 20.8, 1L, ads);
        BillboardEntity billboardEntity = BillboardConverter.toEntity(billboard);
        assertNotNull(billboardEntity);
        assertEquals(billboard.getLocation(), billboardEntity.getLocation());
        assertEquals(billboard.getPrice(), billboardEntity.getPrice());
        assertEquals(billboard.getUserId(), billboardEntity.getUserId());
    }
}