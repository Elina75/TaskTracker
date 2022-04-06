package com.example.tasktracker.Controllers;

import com.example.tasktracker.Converters.Converter;
import com.example.tasktracker.Enum.TaskStatus;
import com.example.tasktracker.Models.Task;
import com.example.tasktracker.Dto.TaskDto;
import com.example.tasktracker.Services.TaskService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//Main controller for actions with tasks
@RestController
@RequestMapping(value = "/tasks")
@Api(value = "", tags = "Task Controller", description = "Allows to work with task data")
public class TaskController {

    TaskService taskService;

    @Autowired
    Converter converter;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    //Get all tasks
    @GetMapping
    @ApiOperation(value = "Get all tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDto> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return converter.entityToDto(tasks);
    }

    //Get task by id
    @GetMapping("/{id}")
    @ApiOperation(value = "Get task by id")
    @ResponseStatus(HttpStatus.OK)
    public TaskDto getProjectById(@PathVariable("id") Long id) {
        Task task = taskService.getTaskById(id);
        return converter.entityToDto(task);
    }

    //Get task by status
    @GetMapping("/taskByStatus")
    @ApiOperation(value = "Get tasks by status")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Labels are showing"),
            @ApiResponse(code = 404, message = "Label was not found with this status")})
    public List<TaskDto> getTasksByStatus(@RequestParam @Valid TaskStatus status) {
        List<Task> tasks = taskService.getTaskByStatus(status);
        return converter.entityToDto(tasks);
    }

    //Add new task
    @PostMapping("/addTask")
    @ApiOperation(value = "Add new task in project")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Status is not correct. Fill in with ToDo, InProgress or Done"),
            @ApiResponse(code = 409, message = "Unexpected error")})
    public ResponseEntity<Void> save(@ApiParam(value = "Adding new task. Id field is unnecessary, you may skip it. Project_id is very important. It shows in which project this task will be. Check the model before creating body") @RequestBody @Valid TaskDto dto) {
        try {
            Task task = converter.dtoToEntity(dto);
            taskService.save(task);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
    }

    //Delete task by id
    @DeleteMapping("/deleteTask/{id}")
    @ApiOperation(value = "Delete task")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 410, message = "Deleted")})
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        try {
            taskService.delete(id);
            return new ResponseEntity<Void>(HttpStatus.GONE);
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    //Update all task's info
    @PutMapping("/updateTask")
    @ApiOperation(value = "Update task")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Status is not correct. Fill in with ToDo, InProgress or Done"),
            @ApiResponse(code = 409, message = "Unexpected error")})
    public ResponseEntity<Void> update(@ApiParam(name = "Task", value = "Info of task you want to update. Check the model before creating body") @RequestBody @Valid TaskDto taskDto) {
        try {
            Task task = converter.dtoToEntityUpdate(taskDto);
            taskService.getTaskById(taskDto.getId());
            taskService.updateAll(task);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }


    //Update only task's status
    @PutMapping("/updateTaskId/{id}/status/{status}")
    @ApiOperation(value = "Update task's status")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Status is not correct. Fill in with ToDo, InProgress or Done"),
            @ApiResponse(code = 409, message = "Unexpected error")})
    public ResponseEntity<Void> updateStatus(@ApiParam( value = "Update status of your task. Enter id and status name") @PathVariable("id") Long id,@PathVariable("status") @Valid TaskStatus status) {
        try {
            taskService.getTaskById(id);
            taskService.updateStatus(id,status);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    //Change the project this task belongs to
    @PutMapping("/updateTask/{id}/projectId/{project_id}")
    @ApiOperation(value = "Update the project this task belongs to")
    @ApiResponses(value = {
            @ApiResponse(code = 409, message = "Unexpected error")})
    public ResponseEntity<Void> updateProjectIdById(@ApiParam( value = "Update the project this task belongs to. Enter id and project id, where you want to add this task") @PathVariable("id") Long id,@PathVariable("project_id") Long project_id) {
        try {
            taskService.getTaskById(id);
            taskService.updateProjectIdById(id,project_id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

}
