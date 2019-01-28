package com.sigecloud.dao.service;

import com.sigecloud.dao.generic.GenericDAOImpl;
import com.sigecloud.dao.generic.IGenericDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Map;

public class GenericServiceImpl<T> implements com.sigecloud.dao.service.IGenericService<T> {

    private IGenericDAO<T> dao;
    private Class<T> cl;


    public GenericServiceImpl(Class<T> cl, SessionFactory sessionFactory) {
        this.cl = cl;
        dao = new GenericDAOImpl<T>(cl, sessionFactory);
    }

    @Override
    public T get(Class<T> cl, Integer id) {
        return (T) dao.get(cl, id);
    }

    @Override
    public T save(T object) {
        return (T) dao.save(object);
    }

    @Override
    public Boolean update(T object) {
        return dao.update(object);
    }

    @Override
    public void delete(T object) {
        dao.delete(object);
    }

    @Override
    public List<T> query(String hsql, Map<String, Object> params) {
        return dao.query(hsql, params);
    }

    @Override
    public List<T> query(String hsql, Map<String, Object> params, int offset, int size) {
        return dao.query(hsql, params, offset, size);
    }

    @Override
    public Integer count(Class<T> cl) {
        return dao.count(cl);
    }

    @Override
    public List<T> getAll() {
        return query("from " + cl.getName(), null);
    }

    @Override
    public void deleteAll() {
        query("delete from " + cl.getName(), null);

    }



}