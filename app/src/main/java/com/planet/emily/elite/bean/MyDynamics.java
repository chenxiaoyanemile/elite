package com.planet.emily.elite.bean;

import cn.bmob.v3.BmobObject;

public class MyDynamics extends BmobObject {

    private UserInfo author;
    private String title;
    private String content;
    private String community;
    private String publish_time;

    public MyDynamics() {

    }

    public MyDynamics(UserInfo author, String title, String content, String community, String publish_time) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.community = community;
        this.publish_time = publish_time;
    }


    public UserInfo getAuthor() {
        return author;
    }

    public void setAuthor(UserInfo author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }

    @Override
    public String toString() {
        return "MyDynamics{" +
                "author=" + author +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", community='" + community + '\'' +
                ", publish_time='" + publish_time + '\'' +
                '}';
    }
}
