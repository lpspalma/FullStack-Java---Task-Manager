package com.asgdx.service;

import com.asgdx.model.Task;
import com.asgdx.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskToReviewService {
    @Autowired
    TaskRepository taskRepository;

    public void change(Task task){
        task.setStatus("accept");
        taskRepository.save(task);
    }

    public List<Task> findAllToReview(){
       return taskRepository.findAllByStatus("pending");
    }

    public void update(Task task) {
        task.setStatus("rejected");
        taskRepository.save(task);
    }
}
