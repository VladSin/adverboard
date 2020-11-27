package org.example.vladsin.adverboard.dao.converter;

import org.example.vladsin.adverboard.dao.entity.BillboardEntity;
import org.example.vladsin.adverboard.model.Billboard;

import java.util.stream.Collectors;

public class BillboardConverter {

    public static Billboard fromEntity(BillboardEntity billboardEntity){
        if(billboardEntity == null){
            return null;
        }
        return new Billboard(
                billboardEntity.getBillboardId(),
                billboardEntity.getLocation(),
                billboardEntity.getPrice(),
                billboardEntity.getUserId(),
                billboardEntity.getAds().stream()
                        .map(AdConverter::fromEntity)
                        .collect(Collectors.toList())
        );
    }

    public static BillboardEntity toEntity(Billboard billboard){
        if(billboard == null){
            return null;
        }
        final BillboardEntity billboardEntity = new BillboardEntity();
        billboardEntity.setBillboardId(billboard.getId());
        billboardEntity.setLocation(billboard.getLocation());
        billboardEntity.setPrice(billboard.getPrice());
        billboardEntity.setUserId(billboard.getUserId());
        billboardEntity.setAds(billboard.getAds().stream()
                .map(AdConverter::toEntity)
                .collect(Collectors.toList()));
        return billboardEntity;
    }
}
