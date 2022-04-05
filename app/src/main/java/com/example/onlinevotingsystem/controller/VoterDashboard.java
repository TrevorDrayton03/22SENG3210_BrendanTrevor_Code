package com.example.onlinevotingsystem.controller;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;

import com.example.onlinevotingsystem.model.Topic;
import com.example.onlinevotingsystem.R;
import com.example.onlinevotingsystem.controller.adapter.VoterDashboardAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

public class VoterDashboard extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    // for storing topics
    ArrayList<Topic> topics = new ArrayList<>();
    static int uID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voter_dashboard);

        // instantiate connection to topics in db
        database = FirebaseDatabase.getInstance("https://onlinevotingsystem-d6144-default-rtdb.firebaseio.com/");
        databaseReference = database.getReference("Topics");

        // get the uID passing through the login screen intent
        Bundle topicIntent = getIntent().getExtras();
        if(topicIntent!=null)
        {
            uID = topicIntent.getInt("uID");
        }

        //read from the db
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int size = 0;
                for (DataSnapshot topicSnapshot : dataSnapshot.getChildren()) {
                    Topic topic = topicSnapshot.getValue(Topic.class);

                    // add the current topic to the list of topics
                    topics = Topic.createTopicsList(++size, topic.getTitle(), topic.getTopicID(), topic.getDate(), topic.getOptions(), uID);
                    // lookup the recyclerview in activity layout
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                    // create adapter passing in the topic data
                    VoterDashboardAdapter adapter = new VoterDashboardAdapter(topics);
                    // Attach the adapter to the recyclerview to populate items
                    recyclerView.setAdapter(adapter);
                    // Set layout manager to position the items
                    recyclerView.setLayoutManager(new LinearLayoutManager(VoterDashboard.this));
                }
            }

            @Override
            public void onCancelled (DatabaseError error){
                //failed to read value
                Log.w("Main activity", "Failed to read value.", error.toException());
            }
        });
    }

    public void openSettings(View view) {
        Intent intent = new Intent(this, Setting.class);
        startActivity(intent);
    }

    public void openViewTopic(View view) {
        Intent intent = new Intent(this, ViewTopic.class);
        intent.putExtra("TopicID", (int) view.getTag());
        intent.putExtra("uID", uID);
        startActivity(intent);
    }
}