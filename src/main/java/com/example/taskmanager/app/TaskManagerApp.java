package com.example.taskmanager.app;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.service.TaskService;

import java.util.List;

public class TaskManagerApp {

    public static void main(String[] args) {
        TaskService taskService  = new TaskService();

        Task t1 = new Task(1, "I am Task 1");
        Task t2 = new Task(2, "I am Task 2");
        Task t3 = new Task(3, "I am Task 3");
        Task t4 = new Task(4, "I am Task 4");

        taskService.addTask(t1);
        taskService.addTask(t2);
        taskService.addTask(t3);
        taskService.addTask(t4);

        List<Task> tasks = taskService .getAllTasks();
        System.out.println("START:");
        printTasks(tasks);

        System.out.println("Completing Evens:");
        taskService.completeTask(t2.getId());
        taskService.completeTask(t4.getId());
        printTasks(tasks);

        // Print original Task 3
        System.out.println("Finding Task 3:");
        Task theThird = taskService.findTaskById(t3.getId());
        System.out.println("Task 3 Before: " + theThird);

        // Modify Task 3
        taskService.updateTaskDescription(theThird.getId(), "Task Three's Brand New Description!!!!");

        // Print updated Task 3
        System.out.println("Task 3 After: " + theThird);


    }

    private static void printTasks(List<Task> tasks) {
        for (Task task : tasks) {
            System.out.println(task.getDescription() + ": Completed? " + task.isCompleted());
        }
    }
}