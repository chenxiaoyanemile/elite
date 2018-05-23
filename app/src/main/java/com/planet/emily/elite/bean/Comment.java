package com.planet.emily.elite.bean;

public class Comment {

    private String name; //评论者
    private String content; //评论内容
    private UserInfo comment;

    public Comment(String name, String content, UserInfo comment) {
        this.name = name;
        this.content = content;
        this.comment = comment;
    }

    public UserInfo getComment() {
        return comment;
    }

    public void setComment(UserInfo comment) {
        this.comment = comment;
    }


    public Comment(){

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
}
