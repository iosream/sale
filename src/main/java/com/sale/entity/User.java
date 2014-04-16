package com.sale.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: wangzhi
 * Date: 4/16/14
 * Time: 5:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @GenericGenerator(name = "system-uuid",strategy="uuid")
    @Column
    private long id;

    @Column
    private String username;

    @Column
    private String password;


}
