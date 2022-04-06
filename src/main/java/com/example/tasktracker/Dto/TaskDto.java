package com.example.tasktracker.Dto;

import com.example.tasktracker.Enum.TaskStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.io.Serializable;

//This class provides the Task DTO(Data transfer object) for requests and responses
@Data
public class TaskDto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name must not be empty. It must contain at least 1 character")
    private String name;

    @NotEmpty(message = "description must not be empty. It must contain at least 1 character")
    private String description;

    @NotNull(message = "status must not be empty. It must contain at least 1 character")
    @ApiModelProperty(dataType = "string", allowableValues = "ToDo, InProgress, Done")
    private TaskStatus status;

    @Min(1)
    @Max(3)
    @Positive(message = "priority must be a number from 1 to 3")
    private int priority;

    @NotNull(message = "Project id must not be empty. It must contain at least 1 character")
    private Long projectId;
}
