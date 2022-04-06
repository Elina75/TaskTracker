package com.example.tasktracker.Repositories;

import com.example.tasktracker.Enum.ProjectStatus;
import com.example.tasktracker.Enum.TaskStatus;
import com.example.tasktracker.Models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
@Transactional
//Project repository
public interface ProjectRepository extends JpaRepository<Project,Long> {

    @Transactional
    @Modifying
    @Query("update Project p set p.name = ?1, p.endDate = ?2, p.startDate = ?3, p.status = ?4, p.priority = ?5 " +
            "where p.id = ?6")
    //Update all Project's fields
    void updateNameOrEndDateOrStartDateOrStatusOrPriorityById(String name, Date endDate, Date startDate, ProjectStatus status, int priority, Long id);

    @Transactional
    @Modifying
    @Query("update Project p set p.status = ?1 where p.id=?2")
    //Update only project's status
    void updateStatusBy(ProjectStatus status, Long id);
}
