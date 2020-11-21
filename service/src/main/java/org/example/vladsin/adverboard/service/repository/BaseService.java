package org.example.vladsin.adverboard.service.repository;

import java.io.Serializable;

public interface BaseService<T> {

    T add(T t);

    T update(T t);

    T get(Serializable id);

    void delete(Serializable id);
}