package org.example.vladsin.adverboard.dao.converter;

import org.example.vladsin.adverboard.dao.entity.AdEntity;
import org.example.vladsin.adverboard.model.Ad;

public class AdConverter {

    public static Ad fromEntity(AdEntity adEntity){
        if(adEntity == null){
            return null;
        }
        return new Ad(
                adEntity.getAdId(),
                adEntity.getLink(),
                adEntity.getBillboardId(),
                adEntity.getVerification()
        );
    }

    public static AdEntity toEntity(Ad ad){
        if(ad == null){
            return null;
        }
        final AdEntity adEntity = new AdEntity();
        adEntity.setAdId(ad.getId());
        adEntity.setLink(ad.getLink());
        adEntity.setBillboardId(ad.getBillboardId());
        adEntity.setVerification(ad.getVerification());
        return adEntity;
    }
}
