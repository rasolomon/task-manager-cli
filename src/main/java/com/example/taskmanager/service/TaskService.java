package com.example.taskmanager.service;

import com.example.taskmanager.model.Task;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaskService {

    private List<Task> tasks = new ArrayList<>();

    public void addTask(Task task ) {
        tasks.add(task);
    }

    public void removeTask(int taskId) {

        Iterator<Task> iterator = tasks.iterator();

        while (iterator.hasNext()) {
            Task task = iterator.next();

            if (task.getId() == taskId) {
                iterator.remove();
                break;
            }
        }
    }

    public List<Task>  getAllTasks() {
        return new ArrayList<>(tasks);
    }

    public void completeTask(int taskId) {
        for (Task task : tasks) {
            if (task.getId() == taskId) {
                task.markCompleted();
            }
        }
    }

    public Task findTaskById(int taskId) {
        for (Task task : tasks) {
            if (task.getId() == taskId) {
                return task;
            }
        }
        return null;
    }
    public List<Task> getCompletedTasks() {
        List<Task> completed = new ArrayList<>();

        for (Task task : tasks) {
            if (task.isCompleted()) {
                completed.add(task);
            }
        }

        return completed;
    }
    public List<Task> getPendingTasks() {
        List<Task> pending = new ArrayList<>();

        for (Task task : tasks) {
            if (!task.isCompleted()) {
                pending.add(task);
            }
        }

        return pending;
    }

    public void updateTaskDescription(int taskId, String newDescription) {
        Task task = findTaskById(taskId);
        if (task != null) {
            task.setDescription(newDescription);
        }
    }

}