package com.sale.dao;

import com.sale.entity.BaseEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wangzhi
 * Date: 4/16/14
 * Time: 2:26 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
@Transactional
public class BaseHibernateDaoImpl extends HibernateDaoSupport implements BaseDao{
    @Override
    public <T extends BaseEntity> T getEntityById(long id, Class<T> clazz) {
        return (T) getHibernateTemplate().get(clazz.getName(), id);
    }

    @Override
    public <T extends BaseEntity> List<T> getEntityByInstance(T instance) {
        return getHibernateTemplate().findByExample(instance);
    }

    @Override
    public <T extends BaseEntity> List<T> getAllEntities(Class<T> clazz, Order order, String orderBy) {
        return createCriteria(clazz, order, orderBy).list();
    }

    @Override
    public <T extends BaseEntity> List<T> getEntityWithPaginationBySql(Class<T> clazz, int page, int pageSize, String sql, List<String> params) {
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
    public Criteria createCriteria(Class clazz, Order order, String orderBy) {
        Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(clazz);

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
    public <T extends BaseEntity> void delete(T instance) {
        if(instance != null) {
            getHibernateTemplate().delete(instance);
        }
    }

    @Override
    public <T extends BaseEntity> void saveOrUpdate(T instance) {
        if(instance.getId() == 0) {
            getHibernateTemplate().save(instance);
        } else {
            getHibernateTemplate().update(instance);
        }
    }
}
