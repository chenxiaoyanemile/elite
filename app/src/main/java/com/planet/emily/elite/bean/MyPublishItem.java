package com.planet.emily.elite.bean;

/**
 * Created by emily on 2018/3/28
 */

public class MyPublishItem {

    private String topic;
    private String content;
    private String community;
    private String publish_time;

    public MyPublishItem(String topic, String content, String community, String publish_time) {
        this.topic = topic;
        this.content = content;
        this.community = community;
        this.publish_time = publish_time;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
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
}
