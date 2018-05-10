package com.planet.emily.elite.bean;

import cn.bmob.v3.BmobObject;

public class PlanetCard extends BmobObject{
    private UserInfo author;
    private String cardTitle;
    private String cardContent;
    private String cardTime;
    private Comment comments;

    public PlanetCard(UserInfo author, String cardTitle, String cardContent, String cardTime, Comment comments) {
        this.author = author;
        this.cardTitle = cardTitle;
        this.cardContent = cardContent;
        this.cardTime = cardTime;
        this.comments = comments;
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

    public String getCardTime() {
        return cardTime;
    }

    public void setCardTime(String cardTime) {
        this.cardTime = cardTime;
    }

    public Comment getComments() {
        return comments;
    }

    public void setComments(Comment comments) {
        this.comments = comments;
    }

    public PlanetCard() {
    }

    @Override
    public String toString() {
        return "PlanetCard{" +
                "author=" + author +
                ", cardTitle='" + cardTitle + '\'' +
                ", cardContent='" + cardContent + '\'' +
                ", cardTime='" + cardTime + '\'' +
                ", comments=" + comments +
                '}';
    }
}
