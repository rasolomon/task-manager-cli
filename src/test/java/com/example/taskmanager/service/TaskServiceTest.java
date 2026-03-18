package com.example.taskmanager.service;

import com.example.taskmanager.service.model.Task;
import com.example.taskmanager.service.service.TaskService;
import org.junit.jupiter.api.Test;
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
    }

    @Test
    void shouldReturnNullWhenTaskDoesNotExist() {
        TaskService service = new TaskService();

        assertNull(service.findTaskById(12345));
    }

}
