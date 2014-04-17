package com.sale.dao;

import com.sale.entity.BaseEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Controller;
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
@Controller(value = "baseHibernateDaoImpl")
public class BaseHibernateDaoImpl<T extends BaseEntity, ID extends Serializable> extends HibernateDaoSupport implements BaseDao<T, ID>{
    @Override
    public <T extends BaseEntity> T getEntityById(ID id, Class clazz) {
        return (T) getHibernateTemplate().get(clazz.getName(), id);
    }

    @Override
    public <T extends BaseEntity> List<T> getEntityByInstance(T instance) {
        return ( List<T>) getHibernateTemplate().findByExample(instance);
    }

    @Override
    public <T extends BaseEntity> List<T> getAllEntities(Class clazz, Order order, String orderBy) {
        return createCriteria(clazz, order, orderBy).list();
    }

    @Override
    public int getCountByCriteria(final Criteria criteria) {
        return Integer.parseInt(String.valueOf(criteria.setProjection(Projections.rowCount()).uniqueResult()));
    }

    @Override
    public <T extends BaseEntity> List<T> getEntityWithPaginationBySql(Class clazz, int page, int pageSize, String sql, List<String> params) {
        String postfix = " limit " + (page - 1) * pageSize + "," + page * pageSize;

        Query query = currentSession().createSQLQuery(sql + postfix);
        if(params != null) {
            int i = 0;
            for(String param : params) {
                query.setParameter(i++, param);
            }
        }
        return query.list();
    }

    @Override
    public Criteria createCriteria(T instance, Order order, String orderBy, Criterion... criterions) {
        Criteria criteria = null;
        if(criterions != null && criterions.length > 0) {
            criteria = createCriteria(instance.getClass(), criterions);
        } else {
            criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(instance.getClass());
        }

        if(order != null && !StringUtils.isEmpty(orderBy)) {
            if(order.isAscending()) {
                criteria.addOrder(Order.asc(orderBy));
            } else {
                criteria.addOrder(Order.desc(orderBy));
            }
        }
        criteria.setFirstResult((instance.getPage() - 1) * instance.getPageSize());
        criteria.setMaxResults(instance.getPageSize());
        return criteria;
    }

    @Override
    public Criteria createCriteria(Class clazz, Order order, String orderBy, Criterion... criterions) {
        Criteria criteria = null;
        if(criterions != null && criterions.length > 0) {
            criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(clazz);
            if(criterions != null) {
                for (Criterion c : criterions)
                    criteria.add(c);
            }
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
    public ID save(T instance) {
        return (ID)getHibernateTemplate().save(instance);
    }

    @Override
    public void update(T instance) {
        getHibernateTemplate().update(instance);
    }
}
