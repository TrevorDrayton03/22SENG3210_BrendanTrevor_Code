package com.example.onlinevotingsystem.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.onlinevotingsystem.R;
import com.example.onlinevotingsystem.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Login extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (Button) findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance("https://onlinevotingsystem-d6144-default-rtdb.firebaseio.com/");
                databaseReference = database.getReference("Users");

                EditText userTextEdit = (EditText) findViewById(R.id.usernameTextInput);
                String username = userTextEdit.getText().toString();
                EditText passwordTextEdit = (EditText) findViewById(R.id.passwordTextInput);
                String password = passwordTextEdit.getText().toString();

                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot topicSnapshot : dataSnapshot.getChildren()) {
                            User user = topicSnapshot.getValue(User.class);

                            if (user.getPassword().equals(password) && user.getUsername().equals(username) && user.getType().equals("voter")) {
                                int uID = user.getUID();
                                openVoterDashboard(uID);
                            } else if (user.getPassword().equals(password) && user.getUsername().equals(username) && user.getType().equals("manager")) {
                                int uID = user.getUID();
                                openManagerDashboard(uID);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        Log.w("Main activity", "Failed to read value.", error.toException());
                    }
                });
            }
        });
    }

    public void openVoterDashboard(int uID) {
        Intent intent = new Intent(this, VoterDashboard.class);
        intent.putExtra("uID", uID);
        startActivity(intent);
    }

    public void openManagerDashboard(int uID) {
        Intent intent = new Intent(this, ManagerDashboard.class);
        intent.putExtra("uID", uID);
        startActivity(intent);
    }
}