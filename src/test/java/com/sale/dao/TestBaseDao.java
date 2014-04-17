package com.sale.dao;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: wangzhi
 * Date: 4/17/14
 * Time: 9:18 AM
 * To change this template use File | Settings | File Templates.
 */

@ContextConfiguration(locations = "classpath*:/webapp/WEB-INF/config/spring/spring-conf.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class TestBaseDao {
    @Autowired
    private BaseHibernateDaoImpl baseHibernateDaoImpl;

}
