package com.sale.dao;

import com.sale.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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
        baseHibernateDao.getAllEntities(User.class, null, null);
    }

//    @Test
//    public void testGetEntityById(ID id, Class clazz) {
//        User user = new User();
//        baseHibernateDaoImpl.getEntityById(id, clazz);
//    }
}
