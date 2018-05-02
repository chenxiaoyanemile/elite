package com.planet.emily.elite.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by emily on 2018/1/30
 */

@Entity
public class UserLogin {
    @Id
    private Long id;
    private String name;
    private String password;
    private String number;
    @Generated(hash = 474259103)
    public UserLogin(Long id, String name, String password, String number) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.number = number;
    }
    @Generated(hash = 180802810)
    public UserLogin() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getNumber() {
        return this.number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
}
