package org.example.vladsin.adverboard.dao.repository;

import org.example.vladsin.adverboard.model.Billboard;

import java.util.List;

public interface BillboardRepositoryDao {

    Billboard saveBillboard(Billboard billboard);

    boolean updateBillboard(Billboard billboard);

    boolean deleteBillboard(long id);

    Billboard getBillboardById(long id);

    Billboard getBillboardByLocation(String location);

    List<Billboard> getBillboards();

    List<Billboard> getBillboardsByUserId(long userId);

    List<Billboard> getBillboardsByGroupId(long userId);
}
