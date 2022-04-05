package com.example.onlinevotingsystem.model;

public class User {
    int uID;
    String username;
    String password;
    String type;

    public User() {

    }

    public User(int uID, String username, String password, String type) {
        this.uID = uID;
        this.username = username;
        this.password = password;
        this.type = type;
    }

    /* GET METHODS */
    public int getUID() {return uID;}
    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public String getType() {return type;}

    /* SET METHODS */
    public void setUID(int uID) {this.uID = uID;}
    public void setUsername(String username) {this.username = username;}
    public void setPassword(String password) {this.password = password;}
    public void setType(String type) {this.type = type;}
}
