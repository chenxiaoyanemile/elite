package com.planet.emily.elite.bean;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

public class UserInfo extends BmobUser{

    private BmobFile photo;

    public UserInfo() {

    }

    public UserInfo(String userId) {
        setObjectId(userId);
    }

    public BmobFile getPhoto() {
        return photo;
    }

    public void setPhoto(BmobFile photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "photo=" + photo +
                '}';
    }
}
