package org.example.vladsin.adverboard.dao.repository.impl;

import org.example.vladsin.adverboard.dao.repository.GroupBillboardDao;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GroupBillboardDaoImpl implements GroupBillboardDao {

    private static final Logger log = LoggerFactory.getLogger(GroupBillboardDaoImpl.class);
    private final SessionFactory factory;

    public GroupBillboardDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }
}
