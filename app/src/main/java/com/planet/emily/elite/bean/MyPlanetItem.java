package com.planet.emily.elite.bean;

public class MyPlanetItem {
    private String sortUrl;
    private String planetName;
    private String planetAvatarUrl;
    private String founderName;

    public MyPlanetItem() {
    }

    public MyPlanetItem(String sortUrl, String planetName, String planetAvatarUrl, String founderName) {
        this.sortUrl = sortUrl;
        this.planetName = planetName;
        this.planetAvatarUrl = planetAvatarUrl;
        this.founderName = founderName;
    }

    public String getSortUrl() {
        return sortUrl;
    }

    public void setSortUrl(String sortUrl) {
        this.sortUrl = sortUrl;
    }

    public String getPlanetName() {
        return planetName;
    }

    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }

    public String getPlanetAvatarUrl() {
        return planetAvatarUrl;
    }

    public void setPlanetAvatarUrl(String planetAvatarUrl) {
        this.planetAvatarUrl = planetAvatarUrl;
    }

    public String getFounderName() {
        return founderName;
    }

    public void setFounderName(String founderName) {
        this.founderName = founderName;
    }

    @Override
    public String toString() {
        return "MyPlanetItem{" +
                "sortUrl='" + sortUrl + '\'' +
                ", planetName='" + planetName + '\'' +
                ", planetAvatarUrl='" + planetAvatarUrl + '\'' +
                ", founderName='" + founderName + '\'' +
                '}';
    }
}
