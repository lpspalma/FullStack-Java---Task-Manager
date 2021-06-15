package com.asgdx.service;

import com.asgdx.model.Task;
import com.asgdx.model.User;
import com.asgdx.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    public void save(Task task) {
        taskRepository.save(task);
    }

    public Task findById(Long id) {
        return taskRepository.getById(id);
    }

    public Set<Task> getTasksByStatus(User user){
        Set<Task> taskList = new HashSet<>();

        for(Task task: taskRepository.findAllByEmpId(user.getId())){
            if(task.getEmpId().equals(user.getId()) && task.getStatus()!=null){
                taskList.add(task);
            }
        }

        return taskList;
    }

    public List<Task> findAllByUser(Long id) {
        return  taskRepository.findAllByEmpId(id);
    }
}
