package com.example.onlinevotingsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.onlinevotingsystem.Model.Adapter.StatisticAdapter;
import com.example.onlinevotingsystem.Model.Topic;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Manager_Dashboard extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    private Button createTopicButton;
    ArrayList<Topic> topics = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_dashboard);

        createTopicButton = (Button) findViewById(R.id.createTopicButton);
        createTopicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateTopicScreen();
            }
        });

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
                    // lookup the recyclerview
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.managerDashboardRecycler);
                    // create adapter, pass in the statistic data
                    StatisticAdapter adapter = new StatisticAdapter(topics);
                    // Attach the adapter to the recyclerview to populate items
                    recyclerView.setAdapter(adapter);
                    // Set layout manager to position the items
                    recyclerView.setLayoutManager(new LinearLayoutManager(Manager_Dashboard.this));
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
        Intent intent = new Intent(this, Create_Vote_Topic.class);
        startActivity(intent);
    }

    public void openSettings(View view) {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
}