package com.sale.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: wangzhi
 * Date: 4/16/14
 * Time: 5:07 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "TBL_USER")
public class User extends BaseEntity {
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
    @Enumerated(EnumType.STRING)
    private GenderType gender;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }
}
