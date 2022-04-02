package com.example.onlinevotingsystem.Model;

import java.util.Date;
import java.util.List;

public class Topic {
    String topicID;
    String description;
    Date date;
    List<String> options;

    public Topic() {

    }

    public Topic(String topicID, String description, Date date) {
        this.topicID = topicID;
        this.description = description;
        this.date = date;
    }

    public String getTopicID() { return topicID; }

    public void setTopicID(String topicID) {
        this.topicID = topicID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "topic" + topicID;
    }
}
