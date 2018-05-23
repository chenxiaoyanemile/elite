package com.planet.emily.elite.bean;

import cn.bmob.v3.BmobObject;

public class PlanetIssue extends BmobObject{

    private UserInfo author;
    private String required;
    private String summary ;
    private String description;
    private String result;
    private String version;
    private String priority;
    private String assignee;
    private PlanetInfo belongPlanet;

    public PlanetIssue() {
    }

    public PlanetIssue(UserInfo author, String required, String summary, String description, String result,
                       String version, String priority, String assignee, PlanetInfo belongPlanet) {
        this.author = author;
        this.required = required;
        this.summary = summary;
        this.description = description;
        this.result = result;
        this.version = version;
        this.priority = priority;
        this.assignee = assignee;
        this.belongPlanet = belongPlanet;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public UserInfo getAuthor() {
        return author;
    }

    public void setAuthor(UserInfo author) {
        this.author = author;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public PlanetInfo getBelongPlanet() {
        return belongPlanet;
    }

    public void setBelongPlanet(PlanetInfo belongPlanet) {
        this.belongPlanet = belongPlanet;
    }

    @Override
    public String toString() {
        return "PlanetIssue{" +
                "author=" + author +
                ", required='" + required + '\'' +
                ", summary='" + summary + '\'' +
                ", description='" + description + '\'' +
                ", result='" + result + '\'' +
                ", version='" + version + '\'' +
                ", priority='" + priority + '\'' +
                ", assignee='" + assignee + '\'' +
                ", belongPlanet=" + belongPlanet +
                '}';
    }
}
