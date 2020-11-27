package org.example.vladsin.adverboard.dao.repository.impl;

import org.example.vladsin.adverboard.dao.repository.AdDao;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdDaoImpl implements AdDao {

    private static final Logger log = LoggerFactory.getLogger(AdDaoImpl.class);
    private final SessionFactory factory;

    public AdDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

}
