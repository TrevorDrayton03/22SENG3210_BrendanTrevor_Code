package com.example.onlinevotingsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.onlinevotingsystem.Model.Topic;
import com.example.onlinevotingsystem.Model.TopicAdapter;

import java.util.ArrayList;

public class View_Topic_Screen extends AppCompatActivity {

    // for storing the options
    ArrayList<Topic> options = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_topic_screen);

        // add the current topic to the list of options
        options = Topic.createOptionsList(5);
        // lookup the recyclerview in activity layout
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.viewTopicRecyclerView);
        // create adapter passing in the topic data
        TopicAdapter adapter = new TopicAdapter(options);
        // Attach the adapter to the recyclerview to populate items
        recyclerView.setAdapter(adapter);
        // Set layout manager to position the items
        recyclerView.setLayoutManager(new LinearLayoutManager(View_Topic_Screen.this));
    }
}