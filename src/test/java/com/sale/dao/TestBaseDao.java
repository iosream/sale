package com.sale.dao;

import com.sale.entity.User;
import org.hibernate.Criteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wangzhi
 * Date: 4/17/14
 * Time: 9:18 AM
 * To change this template use File | Settings | File Templates.
 */

@ContextConfiguration(locations = "classpath*:config/spring/spring-conf.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class TestBaseDao {
    @Resource
    private BaseDao baseHibernateDao;

    @Test
    public void testGetAllEntities() {
        System.out.print(baseHibernateDao.getAllEntities(User.class, null, null).size());
    }

    @Test
    public void testGetEntityById() {
        System.out.print(baseHibernateDao.getEntityById(1L, User.class));
    }

    @Test
    public void testGetEntityByInstance() {
        User user = (User) baseHibernateDao.getEntityById(1L, User.class);
        System.out.print(baseHibernateDao.getEntityByInstance(user));
    }

    @Test
    public void testGetCountByCriteria() {
        User user = (User) baseHibernateDao.getEntityById(1L, User.class);
        Criteria criteria = baseHibernateDao.createCriteria(user, null, null);
        System.out.print(baseHibernateDao.getCountByCriteria(criteria));
    }

    @Test
    public void testGetEntityWithPagination() {
        String sql = "select * from tbl_user tu where username = ? and password = ?";
        List<String> params = new ArrayList<String>();
        params.add("admin");
        params.add("123456");
        baseHibernateDao.getEntityWithPaginationBySql(User.class, 1, 1, sql , params);
    }
//
//    /**
//     *
//     * @param instance
//     * @param order
//     * @param orderBy
//     * @param criterions
//     * @return
//     */
//    public Criteria createCriteria(T instance, Order order, String orderBy, Criterion... criterions);
//
//    /**
//     *
//     * @param clazz
//     * @param order
//     * @param orderBy
//     * @param criterions
//     * @return
//     */
//    public Criteria createCriteria(Class clazz, Order order, String orderBy, Criterion... criterions);
//
//    /**
//     *
//     * @param clazz
//     * @param criterions
//     * @return
//     */
//    public Criteria createCriteria(Class clazz, Criterion... criterions);
//
//    /**
//     *
//     * @param instance
//     */
//    public void delete(T instance);
//
//    /**
//     *
//     * @param instance
//     */
//    public ID save(T instance);
//
//    /**
//     *
//     * @param instance
//     */
//    public void update(T instance);
}
