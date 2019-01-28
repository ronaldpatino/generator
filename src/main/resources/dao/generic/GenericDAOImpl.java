package com.sigecloud.dao.generic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import java.util.List;
import java.util.Map;

public class GenericDAOImpl<T> implements com.sigecloud.dao.generic.IGenericDAO<T> {
    private SessionFactory sessionFactory;

    public GenericDAOImpl(Class<T> cl, SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        if (sessionFactory == null)
            throw new RuntimeException("Session factory is null!!!");
    }

    @Override
    public T get(Class<T> cl, Integer id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        @SuppressWarnings("unchecked")
        T element = (T) session.get(cl, id);
        session.getTransaction().commit();
        return element;
    }

    @Override
    public T save(T object) {

        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.save(object);
            session.getTransaction().commit();
        }
        catch (Exception e){
            object = null;
        }

        return object;
    }

    @Override
    public Boolean update(T object) {

        Boolean updated = false;
        try{
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.update(object);
            session.getTransaction().commit();
            updated = true;
        }
        catch (Exception e){

        }

        return updated;

    }

    @Override
    public void delete(T object) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.delete(object);
        session.getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> query(String hsql, Map<String, Object> params) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        TypedQuery query = session.createQuery(hsql);
        if (params != null) {
            for (String i : params.keySet()) {
                query.setParameter(i, params.get(i));
            }
        }

        List<T> result = null;
        if ((!hsql.toUpperCase().contains("DELETE"))
                && (!hsql.toUpperCase().contains("UPDATE"))
                && (!hsql.toUpperCase().contains("INSERT"))) {
            result = query.getResultList();
        }
        session.getTransaction().commit();

        return result;
    }


    @SuppressWarnings("unchecked")
    @Override
    public List<T> query(String hsql, Map<String, Object> params, int offset, int size) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        TypedQuery query = session.createQuery(hsql);
        if (params != null) {
            for (String i : params.keySet()) {
                query.setParameter(i, params.get(i));
            }
        }

        List<T> result = null;
        if ((!hsql.toUpperCase().contains("DELETE"))
                && (!hsql.toUpperCase().contains("UPDATE"))
                && (!hsql.toUpperCase().contains("INSERT"))) {
            query.setFirstResult(offset);
            query.setMaxResults(size);
            result = query.getResultList();
        }
        session.getTransaction().commit();

        return result;
    }

    @Override
    public Integer count(Class<T> cl) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Integer rowCount =0;

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        cq.select(cb.count(cq.from(cl)));
        ParameterExpression<Integer> p = cb.parameter(Integer.class);
        Long lcount = (Long) session.createQuery(cq).getSingleResult();
        if (lcount != null){
            rowCount = lcount.intValue();
        }

        session.getTransaction().commit();
        return rowCount;
    }

}