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
        AdEntity adEntity = new AdEntity();
        adEntity.setAdId(null);
        adEntity.setLink("link");
        adEntity.setBillboardId(1L);
        adEntity.setVerification("verified");


        Ad ad = AdConverter.fromEntity(adEntity);
        assertNotNull(ad);
        assertEquals(ad.getId(), adEntity.getAdId());
        assertEquals(ad.getLink(), adEntity.getLink());
        assertEquals(ad.getBillboardId(), adEntity.getBillboardId());
    }

    @Test
    void toEntity() {
        Ad ad = new Ad(null, "link", 1L, "verified");
        AdEntity adEntity = AdConverter.toEntity(ad);
        assertNotNull(adEntity);
        assertEquals(ad.getLink(), adEntity.getLink());
        assertEquals(ad.getBillboardId(), adEntity.getBillboardId());
    }
}