package com.example.taskmanager.service;

import com.example.taskmanager.model.Task;
import java.util.ArrayList;
import java.util.List;

public class TaskService {

    private List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void listTasks() {
        for (Task task : tasks) {
            System.out.println(task);
        }
    }
}