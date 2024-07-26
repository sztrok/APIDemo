package com.example.apidemo.models;

/**
 * Simple user class with basic getters and setters
 */
public class User {
    private String username;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
