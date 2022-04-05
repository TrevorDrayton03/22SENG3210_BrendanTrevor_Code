package com.example.onlinevotingsystem.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.example.onlinevotingsystem.model.Topic;
import com.example.onlinevotingsystem.controller.adapter.ViewTopicAdapter;
import com.example.onlinevotingsystem.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ViewTopic extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    DatabaseReference databaseUpdateReference;

    Topic options = new Topic();
    Map<String, Object> updatedTopic = new HashMap<>();
    static int topicID;
    static int uID;

    // this onCreate receives a Topic ID passed from the Voter_Dashboard:openViewTopic() intent
    // then it searches the topics in the database for the one that has the topic id
    // then it extracts the options and sends them to the OptionAdapter to dynamically load them
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_topic_screen);

        // instantiate connection to topics in db
        database = FirebaseDatabase.getInstance("https://onlinevotingsystem-d6144-default-rtdb.firebaseio.com/");
        databaseReference = database.getReference("Topics");

        // get the topic ID and uID passing through the intent from Voter_Dashboard::openViewTopic()
        Bundle topicIntent = getIntent().getExtras();
        if (topicIntent != null) {
            topicID = topicIntent.getInt("TopicID");
            Log.d("ViewTopicScreen", "Passed topic id is : " + topicID);
            uID = topicIntent.getInt("uID");
            Log.d("amazing", "Passed uID is : " + uID);

        }

        //read from the db
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot topicSnapshot : dataSnapshot.getChildren()) {
                    Topic topic = topicSnapshot.getValue(Topic.class);
                    if (topic.getTopicID() == topicID) {
                        // add the current topic to the list of options
                        options = Topic.createOptionsList(topic.getTitle(), topic.getTopicID(), topic.getDate(), topic.getOptions(), uID);
                        // lookup the recyclerview in activity layout
                        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.viewTopicRecyclerView);
                        // create adapter passing in the topic data
                        ViewTopicAdapter adapter = new ViewTopicAdapter(options);
                        // Attach the adapter to the recyclerview to populate items
                        recyclerView.setAdapter(adapter);
                        // Set layout manager to position the items
                        recyclerView.setLayoutManager(new LinearLayoutManager(ViewTopic.this));
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                //failed to read value
                Log.w("Main activity", "Failed to read value.", error.toException());
            }
        });
    }

    // this is the onClick for when a switch is pressed to change the option in the DB
    public void updateOption(View view) {
        // this gets the vote count of the option
        int value = (Integer) view.getTag();

        // this gets the key
        String updatedKey = ((Switch) view).getText().toString();
        Log.d("ViewTopicScreen", "Option Key is : " + updatedKey);

        // this is the Key value pair that's going to update the DB
        Map<String, Integer> childUpdates = new HashMap<>();
        childUpdates.put(updatedKey, value);

        database = FirebaseDatabase.getInstance("https://onlinevotingsystem-d6144-default-rtdb.firebaseio.com/");
        databaseReference = database.getReference("Topics");

        int finalValue = value + 1;
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot topicSnapshot : dataSnapshot.getChildren()) {
                    Topic topic = topicSnapshot.getValue(Topic.class);
                    Map<String, Integer> topicOptions = topic.getOptions();
                    for (String key : topicOptions.keySet()) {
                        if (key == updatedKey) {
                            if ((topic.getTopicVoters().contains(uID)) == false) {
                                ArrayList<Integer> topicVoters = topic.getTopicVoters();
                                topicVoters.add(uID);
                                topicOptions.put(updatedKey, finalValue);
                                String topicKey = topicSnapshot.getKey();
                                updatedTopic.put("date", topic.getDate());
                                updatedTopic.put("topicID", topic.getTopicID());
                                updatedTopic.put("title", topic.getTitle());
                                updatedTopic.put("options", topicOptions);
                                updatedTopic.put("topicVoters", topicVoters);

                                databaseUpdateReference = database.getReference("Topics").child(topicKey);
                                databaseUpdateReference.updateChildren(updatedTopic);
                                Toast.makeText(ViewTopic.this,
                                        "You have successfully voted!", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(ViewTopic.this,
                                        "You have already voted on this topic!", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("Main activity", "Failed to read value.", error.toException());
            }
        });
    }
}