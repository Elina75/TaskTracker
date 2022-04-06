package com.example.tasktracker.Controllers;

import com.example.tasktracker.Converters.Converter;
import com.example.tasktracker.Dto.ProjectDtoRequest;
import com.example.tasktracker.Dto.ProjectDtoResponse;
import com.example.tasktracker.Enum.ProjectStatus;
import com.example.tasktracker.Models.Project;
import com.example.tasktracker.Services.ProjectService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//Main controller for actions with projects
@RestController
@RequestMapping(value = "/projects")
@Api(value = "", tags = "Project Controller", description = "Allows to work with project data")
public class ProjectController {

    ProjectService projectService;

    @Autowired
    Converter converter;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    //Get all projects
    @GetMapping
    @ApiOperation(value = "Get all projects")
    @ResponseStatus(HttpStatus.OK)
    public List<ProjectDtoResponse> getAllTasks() {
        List<Project> projects = projectService.getAllProjects();
        return converter.projectEntityToDto(projects);
    }

    //Get all projects sorted by your parameter
    @GetMapping("/sorted")
    @ApiOperation(value = "Get all projects sorted")
    @ResponseStatus(HttpStatus.OK)
    public List<ProjectDtoResponse> getAllTasksSort(@ApiParam(value = "Get all projects sorted by your parameter. Like : id, name, priority, and so on") @RequestParam String field) {
        List<Project> projects = projectService.getAllProjectsSortByPriority(field);
        return converter.projectEntityToDto(projects);
    }

    //Get project by id
    @GetMapping("/{id}")
    @ApiOperation(value = "Get project by id")
    @ResponseStatus(HttpStatus.OK)
    public ProjectDtoResponse getProjectById(@PathVariable("id") Long id) {
        Project project = projectService.getProjectById(id);
        return converter.projectEntityToDto(project);
    }

    //Add new project
    @PostMapping("/addProject")
    @ApiOperation(value = "Add new project")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Status is not correct. Fill in with NotStarted, Active or Completed"),
            @ApiResponse(code = 409, message = "Unexpected error")})
    public ResponseEntity<Void> save(@ApiParam(value = "Adding new project. Id field is unnecessary, you may skip it.  Check the model before creating body") @RequestBody @Valid ProjectDtoRequest dto) {
        try {
            Project project = converter.dtoToProjectEntity(dto);
            projectService.save(project);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
    }

    //Delete project by id
    @DeleteMapping("/deleteProject/{id}")
    @ApiOperation(value = "Delete project")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 410, message = "Deleted")})
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        try {
            projectService.delete(id);
            return new ResponseEntity<Void>(HttpStatus.GONE);
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    //Update all projects fields
    @PutMapping("/updateProject")
    @ApiOperation(value = "Update project")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Status is not correct. Fill in with NotStarted, Active or Completed"),
            @ApiResponse(code = 409, message = "Unexpected error")})
    public ResponseEntity<Void> update(@ApiParam(name = "Project", value = "Info of project you want to update. Check the model before creating body") @RequestBody @Valid ProjectDtoRequest projectDtoRequest) {
        try {
            Project project = converter.dtoToProjectEntityUpdate(projectDtoRequest);
            projectService.getProjectById(project.getId());
            projectService.update(project);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    //Update project status
    @PutMapping("/updateProject/{id}/status/{status}")
    @ApiOperation(value = "Update project's status")
    @ApiResponses(value = {
            @ApiResponse(code = 409, message = "Unexpected error")})
    public ResponseEntity<Void> updateStatus(@ApiParam(value = "Update status of your project. Enter id and status name") @PathVariable("id") Long id, @PathVariable("status") @Valid ProjectStatus status) {
        try {
            projectService.getProjectById(id);
            projectService.updateStatus(id, status);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }
}
