package com.exam.service;

import java.util.List;

public interface BaseService<T> {

    T selectByPrimaryKey(Object key);

    int insert(T entity);

    int insertSelective(T entity);

    int delete(Object key);

    int update(T entity);

    int updateNotNull(T entity);

    List<T> select(T entity);

    List<T> selectAll();

}
