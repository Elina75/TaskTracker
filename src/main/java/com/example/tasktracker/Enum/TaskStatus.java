package com.example.tasktracker.Enum;

import io.swagger.annotations.ApiModel;

//This is enum class for task's status values
@ApiModel
public enum TaskStatus {
    ToDo,
    InProgress,
    Done
}
