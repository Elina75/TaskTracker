package com.example.tasktracker.Models;

import com.example.tasktracker.Enum.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

//Project model
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    //Id of project
    private Long id;

    @Column(name = "name")
    //Name of the project
    private String name;

    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    //Started date
    private Date startDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    //Ended date
    private Date endDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    //Project's status
    private ProjectStatus status;

    @Column(name = "priority")
    //Project's priority from 1 to 3
    private int priority;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    //Task which are in this project
    private List<Task> task;

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", start_date=" + startDate +
                ", end_date=" + endDate +
                ", status=" + status +
                ", priority=" + priority +
                ", task=" + task +
                '}';
    }
}
