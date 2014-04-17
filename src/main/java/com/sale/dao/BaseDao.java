package com.sale.dao;

import com.sale.domain.BaseDomain;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wangzhi
 * Date: 4/16/14
 * Time: 2:01 PM
 * To change this template use File | Settings | File Templates.
 */
public interface BaseDao<T extends BaseDomain, ID extends Serializable> {
    /**
     *
     * @param id
     * @param clazz
     * @return
     */
    public <T extends BaseDomain> T getEntityById(ID id, Class clazz);

    /**
     *
     * @param instance
     * @return
     */
    public <T extends BaseDomain> T getEntityByInstance(T instance);

    /**
     *
     *
     * @param clazz
     * @param order
     *@param orderBy @return
     */
    public <T extends BaseDomain> List<T> getAllEntities(Class clazz, Order order, String orderBy);

    /**
     *
     * @param criteria
     * @return
     */
    public int getCountByCriteria(final Criteria criteria);

    /**
     *
     *
     *
     *
     * @param instance
     * @param order
     * @param orderBy
     * @param criterions    @return     */
    public <T extends BaseDomain> List<T> getEntityWithPagination(T instance, Order order, String orderBy, Criterion... criterions);

    /**
     *
     *
     *
     * @param instance
     * @param order
     * @param orderBy
     * @param criterions
     * @return
     */
    public Criteria createCriteria(T instance, Order order, String orderBy, Criterion... criterions);

    /**
     *
     *
     * @param clazz
     * @param order
     * @param orderBy
     * @param criterions
     * @return
     */
    public Criteria createCriteria(Class clazz, Order order, String orderBy, Criterion... criterions);

    /**
     *
     * @param clazz
     * @param criterions
     * @return
     */
    public Criteria createCriteria(Class clazz, Criterion... criterions);

    /**
     *
     * @param instance
     */
    public void delete(T instance);

    /**
     *
     * @param instance
     */
    public ID save(T instance);

    /**
     *
     * @param instance
     */
    public void update(T instance);
}
