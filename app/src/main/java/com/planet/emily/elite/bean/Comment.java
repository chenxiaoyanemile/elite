package com.planet.emily.elite.bean;

public class Comment {

    private String name; //评论者
    private String content; //评论内容
    public Comment(){

    }
    public Comment(String name, String content){
        this.name = name;
        this.content = content;
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
