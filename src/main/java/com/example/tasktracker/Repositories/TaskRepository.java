package com.example.tasktracker.Repositories;

import com.example.tasktracker.Enum.TaskStatus;
import com.example.tasktracker.Models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
//Task Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Transactional(readOnly = true)
    //Find task by status
    List<Task> findByStatus(TaskStatus status);

    @Transactional
    @Modifying
    @Query("update Task t set t.status = ?1 where t.id=?2")
    //Update task's status with concrete id
    void updateStatusBy(TaskStatus status, Long id);

    @Modifying
    @Query("update Task t set t.name = ?1, t.description = ?2, t.priority = ?3, t.status = ?4 where t.id = ?5")
   //Update all task's fields
    void updateNameAndDescriptionAndPriorityById(String name, String description, int priority, TaskStatus status, Long id);

    @Modifying
    @Query("update Task t set t.project.id = ?2 where t.id = ?1")
    //Update project this task belongs to
    void updateProjectIdById(Long id,Long project_id);


}
