package org.example.vladsin.adverboard.service.repository.impl;

import org.example.vladsin.adverboard.dao.repository.BaseDao;
import org.example.vladsin.adverboard.service.repository.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
@Transactional
public class BaseServiceImpl<T> implements BaseService<T> {

    private final BaseDao<T> baseDao;

    @Autowired
    public BaseServiceImpl(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    public T add(T t) {
        baseDao.add(t);
        return t;
    }

    public T update(T t) {
        return baseDao.update(t);
    }

    public T get(Serializable id) {
        return baseDao.get(id);
    }

    public void delete(Serializable id) {
        baseDao.delete(id);
    }
}