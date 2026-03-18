package com.example.taskmanager.service.service;

import com.example.taskmanager.service.model.Task;
import java.util.ArrayList;
import java.util.List;

public class TaskService {

    private final List<Task> tasks = new ArrayList<>();

    public void addTask(Task task ) {
        tasks.add(task);
    }

    public void removeTask(int taskId) {
        tasks.removeIf(task -> task.getId() == taskId);
    }

    public List<Task>  getAllTasks() {
        return new ArrayList<>(tasks);
    }

    public void completeTask(int taskId) {
        Task task = findTaskById(taskId);
        if (task != null) {
            task.markCompleted();
        }
    }

    public Task findTaskById(int taskId) {
        return tasks.stream()
                .filter(task -> task.getId() == taskId)
                .findFirst()
                .orElse(null);
    }
    public List<Task> getCompletedTasks() {
        return tasks.stream()
                .filter(Task::isCompleted)
                .toList();
    }
    public List<Task> getPendingTasks() {
        return tasks.stream()
                .filter(task -> !task.isCompleted())
                .toList();
    }

    public void updateTaskDescription(int taskId, String newDescription) {
        Task task = findTaskById(taskId);
        if (task != null) {
            task.setDescription(newDescription);
        }
    }

}