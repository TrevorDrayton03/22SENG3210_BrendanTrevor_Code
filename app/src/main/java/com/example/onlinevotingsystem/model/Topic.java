package com.example.onlinevotingsystem.model;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Topic {
    static ArrayList<Topic> topics = new ArrayList<>();
    public ArrayList<Integer> topicVoters = new ArrayList<>();
    public Map<String, Integer> options = new HashMap<>();

    String title;
    int topicID;
    String date;
    int currentVoter;

    public Topic() {

    }

    public Topic(String title, int topicID, String date, Map<String, Integer> options, int uID) {
        this.title = title;
        this.topicID = topicID;
        this.date = date;
        this.options = options;
        this.currentVoter = uID;
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
    public static ArrayList<Topic> createTopicsList(int size, String title, int topicID, String date, Map<String, Integer> options, int uID) {
        topics.add(new Topic( title,  topicID,  date, options, uID));

        // this ensures that the topics do not add up forever,
        // that the buttons that are generated from the topics list are never higher in count than they're supposed to be
        if(topics.size() > size) {
            topics.clear();
            createTopicsList( size,  title,  topicID,   date,  options,uID);
        }
        return topics;
    }

    /**
     *
     * @param
     * @return
     */
    public static Topic createOptionsList(String title, int topicID, String date, Map<String, Integer> options, Integer uID) {

        Topic topic = new Topic(title, topicID, date, options, uID);

        return topic;
    }

    public void addUserToTopicVotersList(int uID) {
        topicVoters.add(uID);
    }

    /* GET METHODS */
    public String getTitle() { return title; }
    public int getTopicID() { return topicID; }
    public int getCurrentVoter() { return currentVoter; }
    public String getDate() {  return date; }
    public Map<String, Integer> getOptions() { return options; }
    public ArrayList<Integer> getTopicVoters() { return topicVoters; }

    /* SET METHODS */
    public void setTitle(String title) { this.title = title; }
    public void setTopicID(int topicID) { this.topicID = topicID; }
    public void setCurrentVoter(int uID) { this.currentVoter = uID; }
    public void setDate(String date) { this.date = date; }
    public void setOptions(Map<String, Integer> options) { this.options = options; }
}
