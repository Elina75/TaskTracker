package com.example.tasktracker.Converters;

import com.example.tasktracker.Dto.ProjectDtoRequest;
import com.example.tasktracker.Dto.ProjectDtoResponse;
import com.example.tasktracker.Dto.TaskDto;
import com.example.tasktracker.Models.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
//This class is responsible for converting entity to Dto and visa versa
public class Converter {


    //Convert the Task entity into TaskDto
    public TaskDto entityToDto(Task task) {
        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setName(task.getName());
        dto.setDescription(task.getDescription());
        dto.setPriority(task.getPriority());
        dto.setStatus(task.getStatus());
        dto.setProjectId(task.getProject().getId());

        return dto;
    }

    //Convert the TaskDto into Task entity
    public Task dtoToEntity(TaskDto dto) {
        Task task = new Task();
        Project project = new Project();
        project.setId(dto.getProjectId());
        task.setName(dto.getName());
        task.setDescription(dto.getDescription());
        task.setPriority(dto.getPriority());
        task.setStatus(dto.getStatus());
        task.setProject(project);

        return task;

    }

    //Convert tha TaskDto into Task entity with id, for update method
    public Task dtoToEntityUpdate(TaskDto dto) {
        Task task = new Task();
        Project project = new Project();
        project.setId(dto.getProjectId());
        task.setId(dto.getId());
        task.setName(dto.getName());
        task.setDescription(dto.getDescription());
        task.setPriority(dto.getPriority());
        task.setStatus(dto.getStatus());
        task.setProject(project);

        return task;

    }

    //Convert the List of Task entity into TaskDto
    public List<TaskDto> entityToDto(List<Task> tasks){

        return tasks.stream().map(x->entityToDto(x)).collect(Collectors.toList());
    }

    //Convert the List of TaskDto into Task entity
    public List<Task> dtoToEntity(List<TaskDto> dto){
        return dto.stream().map(x->dtoToEntity(x)).collect(Collectors.toList());
    }

    //Convert the Project entity into ProjectDto
    public ProjectDtoResponse projectEntityToDto(Project project) {
        ProjectDtoResponse dto = new ProjectDtoResponse();
        dto.setId(project.getId());
        dto.setName(project.getName());
        dto.setStartDate(project.getStartDate());
        dto.setEndDate(project.getEndDate());
        dto.setPriority(project.getPriority());
        dto.setStatus(project.getStatus());
        dto.setTask(entityToDto(project.getTask()));

        return dto;
    }

    //Convert tha ProjectRequestDto into Project entity
    public Project dtoToProjectEntity(ProjectDtoRequest dto) {
        Project project = new Project();
        project.setName(dto.getName());
        project.setStartDate(dto.getStartDate());
        project.setEndDate(dto.getEndDate());
        project.setPriority(dto.getPriority());
        project.setStatus(dto.getStatus());
        return project;
    }

    //Convert tha ProjectRequestDto into Project entity with id, for update method
    public Project dtoToProjectEntityUpdate(ProjectDtoRequest dto) {
        Project project = new Project();
        project.setId(dto.getId());
        project.setName(dto.getName());
        project.setStartDate(dto.getStartDate());
        project.setEndDate(dto.getEndDate());
        project.setPriority(dto.getPriority());
        project.setStatus(dto.getStatus());
        return project;
    }

    //Convert the List of Projects entity into ProjectDto
    public List<ProjectDtoResponse> projectEntityToDto(List<Project> projects){

        return projects.stream().map(x->projectEntityToDto(x)).collect(Collectors.toList());
    }

    //Convert the List of ProjectDto into Project entity
    public List<Project> dtoToProjectEntity(List<ProjectDtoRequest> dto){
        return dto.stream().map(x->dtoToProjectEntity(x)).collect(Collectors.toList());
    }
}
