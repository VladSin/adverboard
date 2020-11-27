package org.example.vladsin.adverboard.dao.converter;

import org.example.vladsin.adverboard.dao.entity.AdEntity;
import org.example.vladsin.adverboard.model.Ad;

import java.util.stream.Collectors;

public class AdConverter {

    public static Ad fromEntity(AdEntity adEntity){
        if(adEntity == null){
            return null;
        }
        return new Ad(
                adEntity.getId(),
                adEntity.getLink(),
                adEntity.getBillboards().stream()
                        .map(BillboardConverter::fromEntity)
                        .collect(Collectors.toList())
        );
    }

    public static AdEntity toEntity(Ad ad){
        if(ad == null){
            return null;
        }
        final AdEntity adEntity = new AdEntity();
        adEntity.setId(ad.getId());
        adEntity.setLink(ad.getLink());
        adEntity.setBillboards(ad.getBillboards().stream()
                .map(BillboardConverter::toEntity)
                .collect(Collectors.toList()));
        return adEntity;
    }
}
