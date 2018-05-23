package com.planet.emily.elite.bean;

import cn.bmob.v3.BmobObject;

public class Comment extends BmobObject{

    private String name; //评论者
    private String content; //评论内容
    private UserInfo commenter;
    private PlanetCard belongCard;

    public PlanetCard getBelongCard() {
        return belongCard;
    }

    public void setBelongCard(PlanetCard belongCard) {
        this.belongCard = belongCard;
    }



    public Comment(String name, String content, UserInfo commenter, PlanetCard belongCard) {
        this.name = name;
        this.content = content;
        this.commenter = commenter;
        this.belongCard = belongCard;
    }

    public Comment(){

    }

    public UserInfo getCommenter() {
        return commenter;
    }

    public void setCommenter(UserInfo commenter) {
        this.commenter = commenter;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", commenter=" + commenter +
                ", belongCard=" + belongCard +
                '}';
    }
}
