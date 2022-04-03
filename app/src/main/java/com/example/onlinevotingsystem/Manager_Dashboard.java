package com.example.onlinevotingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class Manager_Dashboard extends AppCompatActivity {
private Button createTopicButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_dashboard);

        createTopicButton = (Button) findViewById(R.id.createTopicButton);
        createTopicButton.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick(View v){openCreateTopicScreen();}
        });
    }

    public void openCreateTopicScreen(){
        Intent intent = new Intent(this, Create_Vote_Topic.class);
        startActivity(intent);
    }

    public void openSettings(View view) {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
}