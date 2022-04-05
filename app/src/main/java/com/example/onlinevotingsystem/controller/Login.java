package com.example.onlinevotingsystem.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.onlinevotingsystem.R;
import com.example.onlinevotingsystem.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class Login extends AppCompatActivity {
    // instantiate connection to topics in db
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    private Button loginButton;
    private EditText usernameTextEdit;
    private EditText passwordTextEdit;
    private String username;
    private String password;

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

                //get the user and password from the textEdits
                EditText userTextEdit = (EditText) findViewById(R.id.usernameTextInput);
                String username = userTextEdit.getText().toString();
                EditText passwordTextEdit = (EditText) findViewById(R.id.passwordTextInput);
                String password = passwordTextEdit.getText().toString();
                Log.d("isitset", " " + username + " + " + password);


                //compare them with the records in the DB

                //if true then get userID, check type, go to correct dashboard

                //if false ??

                //read from the db
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot topicSnapshot : dataSnapshot.getChildren()) {
                            User user = topicSnapshot.getValue(User.class);

                            if (user.getPassword().equals(password) && user.getUsername().equals(username) && user.getType().equals("voter")) {
                                Log.d("user snapshot", "True " + user.getUsername());
                                openVoterDashboard();
                            } else if (user.getPassword().equals(password) && user.getUsername().equals(username) && user.getType().equals("manager")) {
                                openManagerDashboard();
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

    public void openVoterDashboard() {
        Intent intent = new Intent(this, VoterDashboard.class);
        startActivity(intent);
    }

    public void openManagerDashboard() {
        Intent intent = new Intent(this, ManagerDashboard.class);
        startActivity(intent);
    }
}