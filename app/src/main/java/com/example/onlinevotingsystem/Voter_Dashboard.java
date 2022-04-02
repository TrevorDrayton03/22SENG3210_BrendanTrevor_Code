package com.example.onlinevotingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Voter_Dashboard extends AppCompatActivity {
    private Button settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voter_dashboard);

        settingsButton = (Button) findViewById(R.id.voterDashboardSettingsButton);
        settingsButton.setOnClickListener(v -> openSettings());
    }

    /**
     * Opens the settings view
     */
    public void openSettings() {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
}