package com.example.onlinevotingsystem;

import static org.junit.Assert.assertEquals;

import com.example.onlinevotingsystem.model.Topic;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/*
This tests the createOptionsList function within the Topic model
 */
public class TopicModelUnitTest {
    @Test
    public void createOptionsList_test() {
        String title = "testTitle";
        int topicID = 1;
        String date = "02/03/2004";
        Map<String, Integer> optionsMap = new HashMap<String, Integer>();
        optionsMap.put("testOption1", 0);
        optionsMap.put("testOption2", 0);
        optionsMap.put("testOption3", 0);

        Topic testTopic = new Topic(title, topicID, date, optionsMap, 1);
        Topic testCreateOptionsListTopic = testTopic.createOptionsList(title, topicID, date, optionsMap, 1);

        assertEquals(testCreateOptionsListTopic.getOptions(), testTopic.getOptions());
    }
}
