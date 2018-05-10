package com.planet.emily.elite.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class PlanetInfo extends BmobObject{

    private String planetName;
    private BmobFile photo;
    private String planetDescription;
    private String type;

    public PlanetInfo() {
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    private UserInfo userInfo;



    public PlanetInfo(String planetName, BmobFile photo, String planetDescription, String type, UserInfo userInfo) {
        this.planetName = planetName;
        this.photo = photo;
        this.planetDescription = planetDescription;
        this.type = type;
        this.userInfo = userInfo;
    }

    public String getPlanetName() {
        return planetName;
    }

    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }

    public BmobFile getPhoto() {
        return photo;
    }

    public void setPhoto(BmobFile photo) {
        this.photo = photo;
    }

    public String getPlanetDescription() {
        return planetDescription;
    }

    public void setPlanetDescription(String planetDescription) {
        this.planetDescription = planetDescription;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PlanetInfo{" +
                "planetName='" + planetName + '\'' +
                ", photo=" + photo +
                ", planetDescription='" + planetDescription + '\'' +
                ", type='" + type + '\'' +
                ", userInfo=" + userInfo +
                '}';
    }
}
