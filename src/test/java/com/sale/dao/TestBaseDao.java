package com.sale.dao;

import com.sale.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

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
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class TestBaseDao {
    @Resource
    private BaseDao baseHibernateDao;

    @Test
    public void testGetAllEntities() {
        assertEquals(baseHibernateDao.getAllEntities(User.class, null, null).size(), 1);
    }

    @Test
    public void testGetEntityById() {
        assertNotNull(baseHibernateDao.getEntityById(1L, User.class));
    }

    @Test
    public void testGetEntityByInstance() {
        User user = new User();
        user.setUsername("admin");
        assertEquals(baseHibernateDao.getEntityByInstance(user).size(), 1);
    }

    @Test
    public void testGetEntityWithPagination() {
        String sql = "select * from TBL_USER tu where username = ? and password = ?";
        List<String> params = new ArrayList<String>();
        params.add("admin");
        params.add("pwd");
        List<User> users =  baseHibernateDao.getEntityWithPaginationBySql(User.class, 1, 1, sql , params);
//        assertEquals(users.size(), 1);
    }

    @Test
    public void delete() {
        User user = new User();
        user.setUsername("admin");
        List<User> users = baseHibernateDao.getEntityByInstance(user);
        if(users != null && !users.isEmpty()) {
            for(User user1: users)
                baseHibernateDao.delete(user1);
        }
    }

    @Test
    public void testSaveOrUpdate() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("pwd");
        user.setNickname("管理员");
        user.setGender(User.GenderType.female);

        baseHibernateDao.saveOrUpdate(user);
//        user.setNickname("管理员2");
//        baseHibernateDao.saveOrUpdate(user);
    }
}
