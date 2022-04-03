package com.example.onlinevotingsystem.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Topic {
    static ArrayList<Topic> topics = new ArrayList<>();
    static ArrayList<Topic> topicOptions = new ArrayList<>();
    String title;
    int topicID;
    String description;
    Date date;
    List<String> options;

    public Topic() {

    }

    public Topic(String title, int topicID, String description, Date date, List<String> options) {
        this.title = title;
        this.topicID = topicID;
        this.description = description;
        this.date = date;
        this.options = options;
    }
    //TODO: write the comments for these functions
    /**
     *
     * @param size
     * @param title
     * @param topicID
     * @param description
     * @param date
     * @param options
     * @return
     */
    public static ArrayList<Topic> createTopicsList(int size, String title, int topicID, String description, Date date, List<String> options) {
        topics.add(new Topic( title,  topicID,  description,  date, options));

        // this ensures that the topics do not add up forever,
        // that the buttons that are generated from the topics list are never higher in count than they're supposed to be
        if(topics.size() > size) {
            topics.clear();
            createTopicsList( size,  title,  topicID,  description,  date,  options);
        }

        return topics;
    }

    /**
     *
     * @param size
     * @return
     */
    public static ArrayList<Topic> createOptionsList(int size) {

        return topics;
    }

    /* GET AND SET METHODS */
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getTopicID() { return topicID; }
    public void setTopicID(int topicID) { this.topicID = topicID; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Date getDate() {  return date; }
    public void setDate(Date date) { this.date = date; }

    public List<String> getOptions() { return options; }
    public void setOptions(List<String> options) { this.options = options; }
}
