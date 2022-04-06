package com.example.tasktracker.Models;

import com.example.tasktracker.Enum.TaskStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "task")
//Task model
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    //Task id
    private Long id;

    @Column(name = "name")
    //Task's name
    private String name;

    @Column(name = "description")
    //Task's description
    private String description;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(dataType = "string", allowableValues = "ToDo, InProgress, Done")
    //Task's status
    private TaskStatus status;

    @Column(name = "priority")
    //Task's priority from 1 to 3
    private int priority;

    @ManyToOne
    @JoinColumn(name = "project_id")
    //Project this task belongs to
    private Project project;

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", priority=" + priority +
                '}';
    }
}
