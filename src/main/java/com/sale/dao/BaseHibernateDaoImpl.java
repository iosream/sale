package com.sale.dao;

import com.sale.domain.BaseDomain;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wangzhi
 * Date: 4/16/14
 * Time: 2:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class BaseHibernateDaoImpl<T extends BaseDomain, ID extends Serializable> extends HibernateDaoSupport implements BaseDao<T, ID>{
    @Override
    public <T extends BaseDomain> T getEntityById(ID id, Class clazz) {
        return (T) getHibernateTemplate().get(clazz.getName(), id);
    }

    @Override
    public <T extends BaseDomain> T getEntityByInstance(T instance) {
        return (T) getHibernateTemplate().findByExample(instance);
    }

    @Override
    public <T extends BaseDomain> List<T> getAllEntities(Class clazz, Order order, String orderBy) {
        return createCriteria(clazz, order, orderBy).list();
    }

    @Override
    public int getCountByCriteria(final Criteria criteria) {
        Integer count = (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();
        return count.intValue();
    }

    @Override
    public <T extends BaseDomain> List<T> getEntityWithPagination(T instance, Order order, String orderBy, Criterion... criterions) {
        Criteria criteria = createCriteria(instance.getClass(), order, orderBy, criterions);
        return criteria.list();
    }

    @Override
    public Criteria createCriteria(T instance, Order order, String orderBy, Criterion... criterions) {
        Criteria criteria = createCriteria(instance.getClass(), order, orderBy, criterions);
        criteria.setFirstResult((instance.getPage() - 1) * instance.getPageSize());
        criteria.setMaxResults(instance.getPageSize());
        return criteria;
    }

    @Override
    public Criteria createCriteria(Class clazz, Order order, String orderBy, Criterion... criterions) {
        Criteria criteria = null;
        if(criterions != null && criterions.length > 0) {
            criteria = createCriteria(clazz, criterions);
        } else {
            criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(clazz);
        }

        if(order != null && !StringUtils.isEmpty(orderBy)) {
            if(order.isAscending()) {
                criteria.addOrder(Order.asc(orderBy));
            } else {
                criteria.addOrder(Order.desc(orderBy));
            }
        }
        return criteria;
    }

    @Override
    public Criteria createCriteria(Class clazz, Criterion... criterions) {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(clazz);
        if(criterions != null) {
            for (Criterion c : criterions)
                criteria.add(c);
        }
        return criteria;
    }

    @Override
    public void delete(T instance) {
        if(instance != null) {
            getHibernateTemplate().delete(instance);
        }
    }

    @Override
    public void delete(ID id, Class clazz) {
        T instance = (T) getEntityById(id, clazz);
        if(instance != null) {
            delete(instance);
        }
    }

    @Override
    public ID save(T instance) {
        return (ID)getHibernateTemplate().save(instance);
    }

    @Override
    public void update(T instance) {
        getHibernateTemplate().update(instance);
    }
}
