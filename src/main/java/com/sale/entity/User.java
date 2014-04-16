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
    public enum GenderType {
        male,
        female;

        public String getGenderInChinese(GenderType genderType) {
            if(genderType == GenderType.male) {
                return "男";
            } else if(genderType == GenderType.female) {
                return "女";
            } else {
                return "未知";
            }
        }
    }
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column
    private String email;

    @Column
    private String mobile;

    @Column(nullable = false)
    private String gender;
}
