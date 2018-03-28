package com.planet.emily.elite.bean;

/**
 * Created by emily on 2018/3/28
 */

public class MyCollectionItem {

    private String content;
    private String community;
    private String author;
    private String time;

    public MyCollectionItem(String content, String community, String author, String time) {
        this.content = content;
        this.community = community;
        this.author = author;
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
