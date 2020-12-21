package org.example.vladsin.adverboard.dao.converter;

import org.example.vladsin.adverboard.dao.entity.BillboardEntity;
import org.example.vladsin.adverboard.dao.entity.GroupBillboardsEntity;
import org.example.vladsin.adverboard.model.Billboard;
import org.example.vladsin.adverboard.model.GroupBillboards;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GroupBillboardsConverterTest {

    @Test
    void fromEntity() {
        List<BillboardEntity> billboardEntities = new ArrayList<>();

        GroupBillboardsEntity groupBillboardsEntity = new GroupBillboardsEntity();
        groupBillboardsEntity.setId(null);
        groupBillboardsEntity.setGroupName("name");
        groupBillboardsEntity.setUserId(1L);

        GroupBillboards groupBillboards = GroupBillboardsConverter.fromEntity(groupBillboardsEntity);
        assertNotNull(groupBillboards);
        assertEquals(groupBillboards.getGroupName(), groupBillboardsEntity.getGroupName());
        assertEquals(groupBillboards.getUserId(), groupBillboardsEntity.getUserId());
    }

    @Test
    void toEntity() {
        List<Billboard> billboards = new ArrayList<>();

        GroupBillboards groupBillboards = new GroupBillboards(null, "name", 1L, billboards);
        GroupBillboardsEntity groupBillboardsEntity = GroupBillboardsConverter.toEntity(groupBillboards);
        assertNotNull(groupBillboardsEntity);
        assertEquals(groupBillboards.getGroupName(), groupBillboardsEntity.getGroupName());
        assertEquals(groupBillboards.getUserId(), groupBillboardsEntity.getUserId());
    }
}