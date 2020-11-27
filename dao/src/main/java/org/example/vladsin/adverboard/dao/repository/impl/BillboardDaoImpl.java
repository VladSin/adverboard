package org.example.vladsin.adverboard.dao.repository.impl;

import org.example.vladsin.adverboard.dao.repository.BillboardDao;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BillboardDaoImpl implements BillboardDao {

    private static final Logger log = LoggerFactory.getLogger(BillboardDaoImpl.class);
    private final SessionFactory factory;

    public BillboardDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

}
