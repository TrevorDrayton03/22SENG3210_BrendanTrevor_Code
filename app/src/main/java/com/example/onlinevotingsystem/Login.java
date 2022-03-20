package com.example.onlinevotingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {
    private Button voterDashboardButton;
    private Button managerDashboardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        voterDashboardButton = (Button) findViewById(R.id.voterDashboardButton);
        managerDashboardButton = (Button) findViewById(R.id.managerDashboardButton);

        voterDashboardButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openVoterDashboard();
            }
        });
        managerDashboardButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openManagerDashboard();
            }
        });
    }
    public void openVoterDashboard(){
        Intent intent = new Intent(this, Voter_Dashboard.class);
        startActivity(intent);
    }
    public void openManagerDashboard(){
        Intent intent = new Intent(this, Manager_Dashboard.class);
        startActivity(intent);
    }
}