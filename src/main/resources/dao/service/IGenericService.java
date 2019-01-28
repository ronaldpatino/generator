package com.sigecloud.dao.service;

import com.sigecloud.dao.generic.IGenericDAO;

import java.util.List;

public interface IGenericService<T> extends IGenericDAO<T> {
    List<T> getAll();
    void deleteAll();
}