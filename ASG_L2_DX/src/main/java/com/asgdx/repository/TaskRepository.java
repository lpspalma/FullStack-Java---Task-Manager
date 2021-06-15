package com.asgdx.repository;

import com.asgdx.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByEmpId(Long id);
    List<Task> findAllByStatus(String status);
}
