package com.example.taskmanager.model;

public class Task {
    private int id;
    private String description;
    private boolean completed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }


    public Task(int id, String description) {
        this.id = id;
        this.description = description;
        this.completed = false;
    }

    public void markCompleted() {
        completed = true;
    }

    @Override
    public String toString() {
        return id + ": " + description + (completed ? " [DONE]" : " [TODO]");
    }
}