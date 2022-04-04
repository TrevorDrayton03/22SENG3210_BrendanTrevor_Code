package com.example.onlinevotingsystem.Model;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Topic {
    static ArrayList<Topic> topics = new ArrayList<>();
    public Map<String, Integer> options = new HashMap<>();

    String title;
    int topicID;
    String date;

    public Topic() {

    }

    public Topic(String title, int topicID, String date, Map<String, Integer> options) {
        this.title = title;
        this.topicID = topicID;
        this.date = date;
        this.options = options;
    }

    @Exclude
    public Map<String, Integer> toMap() {
        HashMap<String, Integer> result = new HashMap<>();
        result.put("Option 1", 0);
        result.put("Option 2", 0);
        result.put("Option 3", 0);
        result.put("Option 4", 0);

        return result;
    }

    //TODO: write the comments for these functions
    /**
     *
     * @param size
     * @param title
     * @param topicID
     * @param date
     * @param options
     * @return
     */
    public static ArrayList<Topic> createTopicsList(int size, String title, int topicID, String date, Map<String, Integer> options) {
        topics.add(new Topic( title,  topicID,  date, options));

        // this ensures that the topics do not add up forever,
        // that the buttons that are generated from the topics list are never higher in count than they're supposed to be
        if(topics.size() > size) {
            topics.clear();
            createTopicsList( size,  title,  topicID,   date,  options);
        }

        return topics;
    }

    /**
     *
     * @param
     * @return
     */
    public static Topic createOptionsList(String title, int topicID, String date, Map<String, Integer> options) {

        Topic topic = new Topic(title, topicID, date, options);

        return topic;
    }

    /* GET AND SET METHODS */
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getTopicID() { return topicID; }
    public void setTopicID(int topicID) { this.topicID = topicID; }

    public String getDate() {  return date; }
    public void setDate(String date) { this.date = date; }

    public Map<String, Integer> getOptions() { return options; }
    public void setOptions(Map<String, Integer> options) { this.options = options; }
}
