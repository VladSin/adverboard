package org.example.vladsin.adverboard.dao.repository;

import org.example.vladsin.adverboard.model.GroupBillboards;


public interface GroupBillboardDao {

    GroupBillboards saveGroup(GroupBillboards group);

    boolean updateGroup(GroupBillboards group);

    boolean deleteGroup(long id);

    GroupBillboards getGroupById(long id);

    GroupBillboards getGroupByUserId(long userId);
}
