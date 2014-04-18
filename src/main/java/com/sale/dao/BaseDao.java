package com.sale.dao;

import com.sale.entity.BaseEntity;
import com.sale.entity.Pagination;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wangzhi
 * Date: 4/16/14
 * Time: 2:01 PM
 * To change this template use File | Settings | File Templates.
 */
public interface BaseDao {
    /**
     * @param id
     * @param clazz
     * @return
     */
    public <T extends BaseEntity> T getEntityById(long id, Class<T> clazz);

    /**
     *
     * @param instance
     * @return
     */
    public <T extends BaseEntity> List<T> getEntityByInstance(T instance);

    /**
     *
     * @param clazz
     * @param order
     * @param orderBy
     * @return
     */
    public <T extends BaseEntity> List<T> getAllEntities(Class<T> clazz, Order order, String orderBy);

    /**
     * @param clazz
     * @param page
     * @param pageSize
     * @param sql
     * @param params
     * */
    public <T extends BaseEntity> List<T> getEntityWithPaginationBySql(Class<T> clazz, int page, int pageSize, String sql, List<String> params);

    /**
     *
     * @param instance
     */
    public <T extends BaseEntity> void delete(T instance);

    /**
     *
     * @param instance
     */
    public <T extends BaseEntity> void saveOrUpdate(T instance);

    /**
     *
     * @param clazz
     * @param order
     * @param orderBy
     * @param <T>
     * @return
     */
    public <T extends BaseEntity> Criteria createCriteria(Class<T> clazz, Order order, String orderBy);
}
