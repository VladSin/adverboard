package org.example.vladsin.adverboard.service.repository;

import org.example.vladsin.adverboard.model.Billboard;
import org.example.vladsin.adverboard.model.GroupBillboards;

import java.util.List;

public interface GroupBillboardService {

    GroupBillboards saveGroup(GroupBillboards group);

    boolean updateGroup(GroupBillboards group);

    boolean deleteGroup(long id);

    GroupBillboards getGroupById(long id);

    GroupBillboards getGroupByUserId(long userId);

    List<GroupBillboards> getGroupsByUserId(long userId);

    List<Billboard> getBillboardsByGroupId(long groupId);
}
