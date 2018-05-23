package com.planet.emily.elite.bean;

import cn.bmob.v3.BmobObject;

public class PlanetProject extends BmobObject{

    private UserInfo founder;
    private String version;
    private String status;
    private String description;
    private PlanetInfo belongPlanet;

    public PlanetProject() {
    }

    public PlanetProject(UserInfo founder, String version, String status, String description, PlanetInfo belongPlanet) {
        this.founder = founder;
        this.version = version;
        this.status = status;
        this.description = description;
        this.belongPlanet = belongPlanet;
    }

    public UserInfo getFounder() {
        return founder;
    }

    public void setFounder(UserInfo founder) {
        this.founder = founder;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PlanetInfo getBelongPlanet() {
        return belongPlanet;
    }

    public void setBelongPlanet(PlanetInfo belongPlanet) {
        this.belongPlanet = belongPlanet;
    }

    @Override
    public String toString() {
        return "PlanetProject{" +
                "founder=" + founder +
                ", version='" + version + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", belongPlanet=" + belongPlanet +
                '}';
    }
}
