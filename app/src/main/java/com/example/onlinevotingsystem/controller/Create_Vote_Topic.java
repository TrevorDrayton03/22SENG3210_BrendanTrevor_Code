package com.example.onlinevotingsystem.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.onlinevotingsystem.model.Topic;
import com.example.onlinevotingsystem.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Create_Vote_Topic extends AppCompatActivity {
    private List<String> optionsList = new ArrayList<String>();
    private Map<String, Integer> optionsMap = new HashMap<String, Integer>();
    ArrayAdapter<String> optionsAdapter;
    DatePickerDialog.OnDateSetListener setListener;
    TextView topicEndDate;

    int optionCount = 0;

    Calendar calendar = Calendar.getInstance();
    final int year = calendar.get(Calendar.YEAR);
    final int month = calendar.get(Calendar.MONTH);
    final int day = calendar.get(Calendar.DAY_OF_MONTH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_vote_topic);

        optionsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, optionsList);

        ListView listView = (ListView) findViewById(R.id.simpleListView);
        listView.setAdapter(optionsAdapter);

        //This is for the date picker fragment
        topicEndDate = findViewById(R.id.datePicker);

        topicEndDate.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Create_Vote_Topic.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        ,setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        }));

        setListener = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month++;
                String date = day+"/"+month+"/"+year;
                topicEndDate.setText(date);
            }
        };
    }

    public void addOption(View view){
        if(optionCount < 4){
            EditText editText=findViewById(R.id.votingOptionTextInput);
            String currentOptionDescription=editText.getText().toString();

            String newOption = new String(currentOptionDescription);
            optionsAdapter.add(newOption);
        }
        optionCount++;
    }

    //Pass the list to the topic class to be input into an object
    public void createTopic(View view) throws ParseException {
        //Firebase initialization
        FirebaseDatabase database;
        DatabaseReference databaseReference;

        database = FirebaseDatabase.getInstance("https://onlinevotingsystem-d6144-default-rtdb.firebaseio.com/");
        databaseReference = database.getReference("Topics");

        //Getting the topic title input
        EditText editText=findViewById(R.id.voteTopicTitleInput);
        String topicTitle=editText.getText().toString();

        //Convert our int format of our date into an actual date format
        String simpleDateInput = year+"-"+month+"-"+day;

        for(int i = 0; i < optionsList.size(); i++)
            optionsMap.put(optionsList.get(i), 0);

        //Inputting our information into a topic object
        // TODO: fix this options here
        Topic newTopic = new Topic(topicTitle, 1, simpleDateInput, optionsMap);

        databaseReference.child(topicTitle).setValue(newTopic);
        Intent intent = new Intent(this, Manager_Dashboard.class);
        startActivity(intent);
    }
}