package com.example.tasktracker.Services;

import com.example.tasktracker.Enum.TaskStatus;
import com.example.tasktracker.Models.Task;
import com.example.tasktracker.Repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//Task service class, provides the logic
public class TaskService {

    TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    //Get all tasks
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    //Add new task
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    //Delete task by id
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    //Get task by id
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).get();
    }

    //Get task by status
    public List<Task> getTaskByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    //Update task's status
    public void updateStatus(Long id, TaskStatus status) {
        taskRepository.updateStatusBy(status, id);
    }

    //Update all fields in task
    public void updateAll(Task task) {
        taskRepository.updateNameAndDescriptionAndPriorityById(task.getName(), task.getDescription(), task.getPriority(), task.getStatus(), task.getId());
    }

    //Update project this task belongs to
    public void updateProjectIdById(Long id, Long project_id) {
        taskRepository.updateProjectIdById(id, project_id);
    }

}
