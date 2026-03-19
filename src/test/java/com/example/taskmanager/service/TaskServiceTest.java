package com.example.taskmanager.service;

import com.example.taskmanager.service.model.Task;
import com.example.taskmanager.service.service.TaskService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskServiceTest {

    @Test
    void shouldAddTask() {
        TaskService service = new TaskService();
        Task task = new Task(1, "Test Task");

        service.addTask(task);

        assertEquals(1, service.getAllTasks().size());
    }

    @Test
    void completeTask() {
        TaskService service = new TaskService();
        Task task = new Task(1, "Test Task");
        service.addTask(task);
        service.completeTask(1);

        assertTrue(service.findTaskById(1).isCompleted());
    }

    @Test
    void removeTask() {
        TaskService service = new TaskService();
        Task task = new Task(1, "Test Task");
        service.addTask(task);
        service.removeTask(1);
        assertTrue(service.getAllTasks().isEmpty());
        assertNull(service.findTaskById(1));

        service.removeTask(123456);
        assertTrue(service.getAllTasks().isEmpty());
    }

    @Test
    void shouldReturnNullWhenTaskDoesNotExist() {
        TaskService service = new TaskService();

        assertNull(service.findTaskById(12345));
    }

    @Test
    void getCompletedTasks() {
        TaskService service = new TaskService();
        Task task1 = new Task(1, "Test Task 1");
        Task task2 = new Task(2, "Test Task 2");
        Task task3 = new Task(3, "Test Task 3");
        Task task4 = new Task(4, "Test Task 4");

        service.addTask(task1);
        service.addTask(task2); service.completeTask(2);
        service.addTask(task3);
        service.addTask(task4); service.completeTask(4);

        List<Task> completed = service.getCompletedTasks();

        assertTrue(completed.contains(task2));
        assertTrue(completed.contains(task4));
        assertEquals(2, completed.size());
    }

    @Test
    void getPendingTasks() {
        TaskService service = new TaskService();
        Task task1 = new Task(1, "Test Task 1");
        Task task2 = new Task(2, "Test Task 2");
        Task task3 = new Task(3, "Test Task 3");
        Task task4 = new Task(4, "Test Task 4");

        service.addTask(task1);
        service.addTask(task2); service.completeTask(2);
        service.addTask(task3);
        service.addTask(task4); service.completeTask(4);

        List<Task> pending = service.getPendingTasks();

        assertTrue(pending.contains(task1));
        assertTrue(pending.contains(task3));
        assertEquals(2, pending.size());
    }

    @Test
    void updateTaskDescription() {
        TaskService service = new TaskService();
        Task task = new Task(1, "Test Task 1");
        service.addTask(task);
        assertEquals("Test Task 1", service.findTaskById(1).getDescription());

        String newDescription = "Rebellious subjects, enemies to peace,\n" +
                "Profaners of this neighbour-stained steel\n" +
                "Will they not hear?  What, ho!  You men, you beasts,\n" +
                "That quench the fire of your pernicious rage";

        service.updateTaskDescription(1, newDescription);

        assertEquals(newDescription, service.findTaskById(1).getDescription());
        assertFalse(service.findTaskById(1).isCompleted());

    }
}
