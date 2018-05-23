package com.planet.emily.elite.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;

public class PlanetInfo extends BmobObject{

    private String planetName;
    private BmobFile photo;
    private String planetDescription;
    private String type;
    private UserInfo userInfo;
    private BmobRelation members;

    public PlanetInfo(String objectId) {
        setObjectId(objectId);
    }

    public PlanetInfo() {
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public PlanetInfo(String planetName, BmobFile photo, String planetDescription, String type, UserInfo userInfo) {
        this.planetName = planetName;
        this.photo = photo;
        this.planetDescription = planetDescription;
        this.type = type;
        this.userInfo = userInfo;
    }



    public BmobRelation getMembers() {
        return members;
    }

    public void setMembers(BmobRelation members) {
        this.members = members;
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
