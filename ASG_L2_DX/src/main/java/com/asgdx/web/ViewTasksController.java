package com.asgdx.web;

import com.asgdx.model.User;
import com.asgdx.service.TaskService;
import com.asgdx.model.Task;
import com.asgdx.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Controller
public class ViewTasksController {
    @Autowired
    TaskService taskService;

    @Autowired
    TaskRepository taskRepository;

    private String tasklist = "taskList";

    @GetMapping("manager/viewalltasks")
    public String allTasks(Model model) {
        List<Task> tasks = taskRepository.findAll();
        model.addAttribute(tasklist, tasks);

        return "manager/view-all-tasks";
    }

    @GetMapping("manager/viewalltasks/{id}")
    public String deleteTask(@PathVariable("id") Long taskId, Model model) {

        try{
            taskRepository.delete(taskService.findById(taskId));
        }
        catch (EntityNotFoundException err){
            model.addAttribute("error","Invalid Data");
        }

        return "redirect:/manager/viewalltasks";
    }

    @GetMapping("employee/viewalltasks")
    public String allTasksEmployee(@AuthenticationPrincipal User user, Model model) {
        List<Task> tasks = taskService.findAllByUser(user.getId());

        tasks.removeIf(task -> task.getStatus() != null && task.getStatus().equals("accept"));
        model.addAttribute(tasklist, tasks);

        return "employee/view-all-tasks";
    }

}
