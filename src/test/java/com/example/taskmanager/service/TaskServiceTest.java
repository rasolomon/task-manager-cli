package com.example.taskmanager.service;

import com.example.taskmanager.service.model.Task;
import com.example.taskmanager.service.service.TaskService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskServiceTest {
    private TaskService createServiceWithTasks() {
        TaskService service = new TaskService();

        service.addTask(new Task(1, "Task 1"));
        service.addTask(new Task(2, "Task 2"));
        service.addTask(new Task(3, "Task 3"));
        service.addTask(new Task(4, "Task 4"));

        return service;
    }
    private TaskService createServiceWithTask() {
        TaskService service = new TaskService();
        service.addTask(new Task(1, "Task 1"));
        return service;
    }

    @Test
    void shouldAddTask() {
        TaskService service = createServiceWithTasks();
        assertEquals(4, service.getAllTasks().size());
    }

    @Test
    void shouldCompleteTask() {
        TaskService service = createServiceWithTasks();
        service.completeTask(1);
        assertTrue(service.findTaskById(1).isCompleted());
    }

    @Test
    void shouldRemoveTask() {
        TaskService service = createServiceWithTask();
        service.removeTask(1);
        assertTrue(service.getAllTasks().isEmpty());
        assertNull(service.findTaskById(1));

        service.removeTask(123456);
        assertTrue(service.getAllTasks().isEmpty());
    }

    @Test
    void shouldReturnNullWhenTaskDoesNotExist() {
        TaskService service = createServiceWithTasks();
        assertNull(service.findTaskById(12345));
    }

    @Test
    void shouldReturnCompletedTasks() {
        TaskService service = createServiceWithTasks();
        service.completeTask(2);
        service.completeTask(4);
        List<Task> completed = service.getCompletedTasks();

        //assertTrue(completed.contains(service.findTaskById(2)));
        //assertTrue(completed.contains(service.findTaskById(4)));
        assertTrue(completed.stream().anyMatch(t -> t.getId() == 2));
        assertTrue(completed.stream().anyMatch(t -> t.getId() == 4));
        assertEquals(2, completed.size());
    }

    @Test
    void shouldReturnPendingTasks() {
        TaskService service = createServiceWithTasks();
        service.completeTask(2);
        service.completeTask(4);
        List<Task> pending = service.getPendingTasks();

        assertTrue(pending.stream().anyMatch(t -> t.getId() == 1));
        assertTrue(pending.stream().anyMatch(t -> t.getId() == 3));
        assertEquals(2, pending.size());
    }

    @Test
    void shouldUpdateTaskDescription() {
        TaskService service = createServiceWithTasks();
        Task task = new Task(1, "Test Task 1");
        service.addTask(task);
        assertEquals("Task 1", service.findTaskById(1).getDescription());

        String newDescription = "Rebellious subjects, enemies to peace,\n" +
                "Profaners of this neighbour-stained steel\n" +
                "Will they not hear?  What, ho!  You men, you beasts,\n" +
                "That quench the fire of your pernicious rage";

        service.updateTaskDescription(1, newDescription);
        assertEquals(newDescription, service.findTaskById(1).getDescription());
        assertFalse(service.findTaskById(1).isCompleted());
    }

    @Test
    void shouldNotCrashWhenUpdatingNonExistentTask() {
        TaskService service = createServiceWithTask();
        int originalSize = service.getAllTasks().size();
        service.updateTaskDescription(123456, "blah blah");
        assertEquals(originalSize, service.getAllTasks().size());
    }

    @Test
    void shouldDoNothingWhenCompletingNonExistentTask() {
        TaskService service = createServiceWithTask();
        int originalSize = service.getAllTasks().size();
        service.completeTask(123456);
        assertEquals(originalSize, service.getAllTasks().size());
    }
}
