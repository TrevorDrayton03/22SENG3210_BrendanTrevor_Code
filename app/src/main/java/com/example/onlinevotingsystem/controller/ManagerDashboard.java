package com.example.onlinevotingsystem.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.onlinevotingsystem.model.Topic;
import com.example.onlinevotingsystem.R;
import com.example.onlinevotingsystem.controller.adapter.ManagerDashboardAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ManagerDashboard extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    DatabaseReference deleteDatabaseReference;

    private Button createTopicButton;
    private Button deleteTopicButton;
    ArrayList<Topic> topics = new ArrayList<>();
    static int uID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_dashboard);

/*
        deleteTopicButton = (Button) findViewById(R.id.deleteTopicButton);
*/
        createTopicButton = (Button) findViewById(R.id.createTopicButton);

        createTopicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateTopicScreen();
            }
        });

        // get the uID passing through the login screen intent
        Bundle topicIntent = getIntent().getExtras();
        if (topicIntent != null) {
            uID = topicIntent.getInt("uID");
        }


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
                    topics = Topic.createTopicsList(++size, topic.getTitle(), topic.getTopicID(), topic.getDate(), topic.getOptions(), uID);
                    // lookup the recyclerview
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.managerDashboardRecycler);
                    // create adapter, pass in the statistic data
                    ManagerDashboardAdapter adapter = new ManagerDashboardAdapter(topics);
                    // Attach the adapter to the recyclerview to populate items
                    recyclerView.setAdapter(adapter);
                    // Set layout manager to position the items
                    recyclerView.setLayoutManager(new LinearLayoutManager(ManagerDashboard.this));
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                //failed to read value
                Log.w("Manager Dashboard", "Failed to read value.", error.toException());
            }
        });
    }

    public void openCreateTopicScreen() {
        Intent intent = new Intent(this, CreateVoteTopic.class);
        startActivity(intent);
    }

    public void openSettings(View view) {
        Intent intent = new Intent(this, Setting.class);
        startActivity(intent);
    }

    public void deleteTopic(View view) {
        int deleteTopicID = (Integer) view.getTag();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot topicSnapshot : dataSnapshot.getChildren()) {
                    Topic topic = topicSnapshot.getValue(Topic.class);
                    if(topic.getTopicID() == deleteTopicID){
                        deleteDatabaseReference = database.getReference("Topics").child(topicSnapshot.getKey());
                        deleteDatabaseReference.removeValue();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                //failed to read value
                Log.w("Manager Dashboard", "Failed to read value.", error.toException());
            }
        });
    }
}