package com.example.onlinevotingsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.onlinevotingsystem.Model.OptionAdapter;
import com.example.onlinevotingsystem.Model.Topic;
import com.example.onlinevotingsystem.Model.TopicAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class View_Topic_Screen extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    // for storing the options
    Topic options = new Topic();
    static int topicID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_topic_screen);

        // instantiate connection to topics in db
        database = FirebaseDatabase.getInstance("https://onlinevotingsystem-d6144-default-rtdb.firebaseio.com/");
        databaseReference = database.getReference("Topics");

        // get the topic ID passing through the intent from Voter_Dashboard::openViewTopic()
        Bundle topicIntent = getIntent().getExtras();
        if(topicIntent!=null)
        {
            topicID = topicIntent.getInt("TopicID");
            Log.d("ViewTopicScreen", "Passed topic id is : " + topicID);
        }

        //read from the db
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot topicSnapshot : dataSnapshot.getChildren()) {
                    Topic topic = topicSnapshot.getValue(Topic.class);
                    if(topic.getTopicID() == topicID)
                    {
                        // add the current topic to the list of options
                        options = Topic.createOptionsList(topic.getTitle(), topic.getTopicID(), topic.getDescription(), topic.getDate(), topic.getOptions());
                        // lookup the recyclerview in activity layout
                        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.viewTopicRecyclerView);
                        // create adapter passing in the topic data
                        OptionAdapter adapter = new OptionAdapter(options);
                        // Attach the adapter to the recyclerview to populate items
                        recyclerView.setAdapter(adapter);
                        // Set layout manager to position the items
                        recyclerView.setLayoutManager(new LinearLayoutManager(View_Topic_Screen.this));
                    }
                }
            }

            @Override
            public void onCancelled (DatabaseError error){
                //failed to read value
                Log.w("Main activity", "Failed to read value.", error.toException());
            }
        });
    }
    // this is the onClick for when a switch is pressed to update the option in the DB
    public void updateOption(View view){
    }
}