package com.planet.emily.elite.bean;

import cn.bmob.v3.BmobObject;

public class PlanetCard extends BmobObject{

    private UserInfo author;
    private String cardTitle;
    private String cardContent;
    private PlanetInfo belongPlanet;


    public PlanetCard(UserInfo author, String cardTitle, String cardContent, PlanetInfo belongPlanet) {
        this.author = author;
        this.cardTitle = cardTitle;
        this.cardContent = cardContent;
        this.belongPlanet = belongPlanet;
    }

    public UserInfo getAuthor() {
        return author;
    }

    public void setAuthor(UserInfo author) {
        this.author = author;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public void setCardTitle(String cardTitle) {
        this.cardTitle = cardTitle;
    }

    public String getCardContent() {
        return cardContent;
    }

    public void setCardContent(String cardContent) {
        this.cardContent = cardContent;
    }


    public PlanetInfo getBelongPlanet() {
        return belongPlanet;
    }

    public void setBelongPlanet(PlanetInfo belongPlanet) {
        this.belongPlanet = belongPlanet;
    }

    public PlanetCard() {
    }

    @Override
    public String toString() {
        return "PlanetCard{" +
                "author=" + author +
                ", cardTitle='" + cardTitle + '\'' +
                ", cardContent='" + cardContent + '\'' +
                ", belongPlanet=" + belongPlanet +
                '}';
    }
}
