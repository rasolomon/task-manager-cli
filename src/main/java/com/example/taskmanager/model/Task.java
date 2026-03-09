package com.example.taskmanager.model;

public class Task {
    private int id;
    private String description;
    private boolean completed;

    public Task(int id, String description) {
        this.id = id;
        this.description = description;
        this.completed = false;
    }

    public void markCompleted() {
        completed = true;
    }

    public String toString() {
        return id + ": " + description + (completed ? " [DONE]" : " [TODO]");
    }
}