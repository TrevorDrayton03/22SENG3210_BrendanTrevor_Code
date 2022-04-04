package com.example.onlinevotingsystem;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;

import com.example.onlinevotingsystem.Model.Topic;
import com.example.onlinevotingsystem.Model.Adapter.TopicAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

public class Voter_Dashboard extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    // for storing topics
    ArrayList<Topic> topics = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voter_dashboard);

        // instantiate connection to topics in db
        database = FirebaseDatabase.getInstance("https://onlinevotingsystem-d6144-default-rtdb.firebaseio.com/");
        databaseReference = database.getReference("Topics");

        //read from the db
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int size = 0;
                for (DataSnapshot topicSnapshot : dataSnapshot.getChildren()) {
                    Topic topic = topicSnapshot.getValue(Topic.class);

                    // add the current topic to the list of topics
                    topics = Topic.createTopicsList(++size, topic.getTitle(), topic.getTopicID(), topic.getDescription(), topic.getDate(), topic.getOptions());
                    // lookup the recyclerview in activity layout
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                    // create adapter passing in the topic data
                    TopicAdapter adapter = new TopicAdapter(topics);
                    // Attach the adapter to the recyclerview to populate items
                    recyclerView.setAdapter(adapter);
                    // Set layout manager to position the items
                    recyclerView.setLayoutManager(new LinearLayoutManager(Voter_Dashboard.this));

                    // topic.setOptions(topic.toMap()) prints:
                    // Options value is: {Option 1=0, Option 2=0, Option 3=0, Option 4=0, Option 5=0, Option 6=0}
                    topic.setOptions(topic.toMap());
                    Log.d("Main activity", "Title value is: " + topic.getTitle());
                    Log.d("Main activity", "Description value is: " + topic.getDescription());
                    Log.d("Main activity", "TopicID value is: " + topic.getTopicID());
                    Log.d("Main activity", "Date value is: " + topic.getDate());
                    Log.d("Main activity", "Options value is: " + topic.getOptions());
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
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    public void openViewTopic(View view) {
        Intent intent = new Intent(this, View_Topic_Screen.class);
        // pass topic ID through this intent, must be typecast to int
        intent.putExtra("TopicID", (int) view.getTag());
        startActivity(intent);
    }
}