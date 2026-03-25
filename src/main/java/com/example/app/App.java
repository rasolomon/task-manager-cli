package com.example.app;

import com.example.taskmanager.service.model.Task;
import com.example.taskmanager.service.service.TaskService;

import java.util.List;
import java.util.Scanner;

public class App {

    private static final TaskService taskService = new TaskService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        boolean running = true;

        System.out.println("Task Manager Started");

        while (running) {
            printMenu();
            String command = scanner.nextLine();
            switch (command.toLowerCase()) {
                    case "add":
                        addTask();
                        break;

                    case "list":
                        listTasks();
                        break;

                    case "complete":
                        completeTask();
                        break;

                    case "delete":
                        deleteTask();
                        break;

                    case "update":
                        updateTask();
                        break;

                    case "exit":
                        running = false;
                        System.out.println("Goodbye!");
                        break;

                    default:
                        System.out.println("Unknown command");

            }
        }
    }
    private static void printMenu() {

        System.out.println();
        System.out.println("Commands:");
        System.out.println("add");
        System.out.println("list");
        System.out.println("complete");
        System.out.println("delete");
        System.out.println("update");
        System.out.println("exit");
        System.out.print("> ");
    }

    private static void addTask() {

        System.out.print("Enter task description: ");
        String description = scanner.nextLine();

        int id = taskService.getAllTasks().size() + 1;

        Task task = new Task(id, description);

        taskService.addTask(task);

        System.out.println("Task added.");
    }

    private static void listTasks() {

        List<Task> tasks = taskService.getAllTasks();

        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }

        for (Task task : tasks) {

            String status = task.isCompleted()
                    ? "[X]"
                    : "[ ]";

            System.out.println(
                    task.getId()
                            + " "
                            + status
                            + " "
                            + task.getDescription()
            );
        }
    }

    private static void completeTask() {

        System.out.print("Enter task ID to complete: ");

        int id = readInt();

        taskService.completeTask(id);

        System.out.println("Task completed (if it existed).");
    }

    private static void deleteTask() {

        System.out.print("Enter task ID to delete: ");

        int id = readInt();

        taskService.removeTask(id);

        System.out.println("Task removed (if it existed).");
    }

    private static void updateTask() {

        System.out.print("Enter task ID to update: ");

        int id = readInt();

        System.out.print("Enter new description: ");

        String description = scanner.nextLine();

        taskService.updateTaskDescription(id, description);

        System.out.println("Task updated (if it existed).");
    }

    private static int readInt() {
        while (true) {
            int theInteger;
            try {
                theInteger = Integer.parseInt(scanner.nextLine());
                return theInteger;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number, please try again");
            }
        }
    }
}