package com.sigecloud.dao.generic;

import java.util.List;
import java.util.Map;

public interface IGenericDAO<T> {
    T get(Class<T> cl, Integer id);
    T save(T object);
    Boolean update(T object);
    void delete(T object);
    List<T> query(String hsql, Map<String, Object> params);
    List<T> query(String hsql, Map<String, Object> params, int offset, int size);
    Integer count(Class<T> cl);

}