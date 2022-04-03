package com.example.onlinevotingsystem.Model;

import java.util.Date;
import java.util.List;

public class Topic {
    public static int topicID = 0;
    String title;
    String description;
    Date date;
    List<String> options;

    public Topic() {

    }

    public Topic(String title, String description, Date date, List<String> options) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.options = options;
    }

    public int getTopicID() { return topicID; }

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
