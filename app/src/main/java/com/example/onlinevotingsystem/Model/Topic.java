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
    String description;
    Date date;

    public Topic() {

    }

    public Topic(String title, int topicID, String description, Date date, Map<String, Integer> options) {
        this.title = title;
        this.topicID = topicID;
        this.description = description;
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
        result.put("Option 5", 0);
        result.put("Option 6", 0);

        return result;
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
    public static ArrayList<Topic> createTopicsList(int size, String title, int topicID, String description, Date date, Map<String, Integer> options) {
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
     * @param
     * @return
     */
    public static Topic createOptionsList(String title, int topicID, String description, Date date, Map<String, Integer> options) {

        Topic topic = new Topic(title, topicID, description, date, options);

        return topic;
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

    public Map<String, Integer> getOptions() { return options; }
    public void setOptions(Map<String, Integer> options) { this.options = options; }
}
