package com.example.tasktracker.Services;

import com.example.tasktracker.Enum.ProjectStatus;
import com.example.tasktracker.Enum.TaskStatus;
import com.example.tasktracker.Models.Project;
import com.example.tasktracker.Models.Task;
import com.example.tasktracker.Repositories.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.mockito.Mockito.*;

class TaskServiceTest {
    @Mock
    TaskRepository taskRepository;
    @InjectMocks
    TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllTasks() {
        List<Task> result = taskService.getAllTasks();
        Assertions.assertEquals(Arrays.<Task>asList(new Task(Long.valueOf(1), "name", "description", TaskStatus.ToDo, 0, new Project(Long.valueOf(1), "name", new GregorianCalendar(2022, Calendar.APRIL, 6, 19, 56).getTime(), new GregorianCalendar(2022, Calendar.APRIL, 6, 19, 56).getTime(), ProjectStatus.NotStarted, 0, Arrays.<Task>asList(null)))), result);
    }

    @Test
    void testSave() {
        Task result = taskService.save(new Task(Long.valueOf(1), "name", "description", TaskStatus.ToDo, 0, new Project(Long.valueOf(1), "name", new GregorianCalendar(2022, Calendar.APRIL, 6, 19, 56).getTime(), new GregorianCalendar(2022, Calendar.APRIL, 6, 19, 56).getTime(), ProjectStatus.NotStarted, 0, Arrays.<Task>asList(null))));
        Assertions.assertEquals(new Task(Long.valueOf(1), "name", "description", TaskStatus.ToDo, 0, new Project(Long.valueOf(1), "name", new GregorianCalendar(2022, Calendar.APRIL, 6, 19, 56).getTime(), new GregorianCalendar(2022, Calendar.APRIL, 6, 19, 56).getTime(), ProjectStatus.NotStarted, 0, Arrays.<Task>asList(null))), result);
    }

    @Test
    void testDelete() {
        taskService.delete(Long.valueOf(1));
    }

    @Test
    void testGetTaskById() {
        Task result = taskService.getTaskById(Long.valueOf(1));
        Assertions.assertEquals(new Task(Long.valueOf(1), "name", "description", TaskStatus.ToDo, 0, new Project(Long.valueOf(1), "name", new GregorianCalendar(2022, Calendar.APRIL, 6, 19, 56).getTime(), new GregorianCalendar(2022, Calendar.APRIL, 6, 19, 56).getTime(), ProjectStatus.NotStarted, 0, Arrays.<Task>asList(null))), result);
    }

    @Test
    void testGetTaskByStatus() {
        when(taskRepository.findByStatus(any())).thenReturn(Arrays.<Task>asList(new Task(Long.valueOf(1), "name", "description", TaskStatus.ToDo, 0, new Project(Long.valueOf(1), "name", new GregorianCalendar(2022, Calendar.APRIL, 6, 19, 56).getTime(), new GregorianCalendar(2022, Calendar.APRIL, 6, 19, 56).getTime(), ProjectStatus.NotStarted, 0, Arrays.<Task>asList(null)))));

        List<Task> result = taskService.getTaskByStatus(TaskStatus.ToDo);
        Assertions.assertEquals(Arrays.<Task>asList(new Task(Long.valueOf(1), "name", "description", TaskStatus.ToDo, 0, new Project(Long.valueOf(1), "name", new GregorianCalendar(2022, Calendar.APRIL, 6, 19, 56).getTime(), new GregorianCalendar(2022, Calendar.APRIL, 6, 19, 56).getTime(), ProjectStatus.NotStarted, 0, Arrays.<Task>asList(null)))), result);
    }

    @Test
    void testUpdateStatus() {
        taskService.updateStatus(Long.valueOf(1), TaskStatus.ToDo);
    }

    @Test
    void testUpdateAll() {
        taskService.updateAll(new Task(Long.valueOf(1), "name", "description", TaskStatus.ToDo, 0, new Project(Long.valueOf(1), "name", new GregorianCalendar(2022, Calendar.APRIL, 6, 19, 56).getTime(), new GregorianCalendar(2022, Calendar.APRIL, 6, 19, 56).getTime(), ProjectStatus.NotStarted, 0, Arrays.<Task>asList(null))));
    }

    @Test
    void testUpdateProjectIdById() {
        taskService.updateProjectIdById(Long.valueOf(1), Long.valueOf(1));
    }
}
