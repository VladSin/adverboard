package org.example.vladsin.adverboard.dao.converter;

import org.example.vladsin.adverboard.dao.entity.GroupBillboardsEntity;
import org.example.vladsin.adverboard.model.GroupBillboards;

import java.util.stream.Collectors;

public class GroupBillboardsConverter {

    public static GroupBillboards fromEntity(GroupBillboardsEntity groupBillboardsEntity){
        if(groupBillboardsEntity == null){
            return null;
        }
        return new GroupBillboards(
                groupBillboardsEntity.getId(),
                groupBillboardsEntity.getGroupName(),
                groupBillboardsEntity.getUserId(),
                null
        );
    }

    public static GroupBillboardsEntity toEntity(GroupBillboards groupBillboards){
        if(groupBillboards == null){
            return null;
        }
        final GroupBillboardsEntity groupBillboardsEntity = new GroupBillboardsEntity();
        groupBillboardsEntity.setId(groupBillboards.getId());
        groupBillboardsEntity.setGroupName(groupBillboards.getGroupName());
        groupBillboardsEntity.setUserId(groupBillboards.getUserId());
        return groupBillboardsEntity;
    }
}
