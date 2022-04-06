package com.example.tasktracker.Dto;

import com.example.tasktracker.Enum.ProjectStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

//This class provides the Project DTO(Data transfer object) for requests
@Data
public class ProjectDtoRequest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name must not be empty. It must contain at least 1 character")
    private String name;

    @ApiModelProperty(name = "startDate",required = true, example = "2020-04-06", notes = "Start date of the project")
    @NotNull(message = "Name must not be empty. It must contain at least 1 character")
    @PastOrPresent(message = "The start date must be before today or today's date")
    private Date startDate;

    @ApiModelProperty(name = "endDate",required = true, example = "2021-04-06", notes = "End date of the project")
    @NotNull(message = "Name must not be empty. It must contain at least 1 character")
    private Date endDate;

    @NotNull(message = "status must not be empty. It must contain at least 1 character")
    @ApiModelProperty(dataType = "string", allowableValues = "NotStarted, Active, Completed")
    private ProjectStatus status;

    @Min(value = 1, message = "Min value of priority is 1. Please try again")
    @Max(value = 3, message = "Max value of priority is 3. Please try again")
    @Positive(message = "priority must be a positive number from 1 to 3")
    private int priority;

}
