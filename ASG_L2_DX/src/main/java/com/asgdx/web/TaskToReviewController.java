package com.asgdx.web;

import com.asgdx.model.Task;
import com.asgdx.model.User;
import com.asgdx.service.TaskService;
import com.asgdx.service.TaskToReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Controller
public class TaskToReviewController {
    @Autowired
    TaskToReviewService taskToReviewService;

    @Autowired
    TaskService taskService;

    @GetMapping("manager/reviewtask")
    public String reviewTask(Model model) {
        List<Task> reviewList = taskToReviewService.findAllToReview();
        model.addAttribute("reviewList", reviewList);
        return "manager/review-task";
    }

    @GetMapping("employee/status")
    public String taskStatus(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("taskList", taskService.getTasksByStatus(user));
        return "employee/task-status";
    }

    @PostMapping("manager/reviewtask/{id}")
    public String reviewTask(@PathVariable("id") Long taskId,
                             @RequestParam(name = "status") String status,
                             Model model) {
        Task task;
        try{
            task = taskService.findById(taskId);
            if(status.equals("accept")){
                taskToReviewService.change(task);
            }else{
                taskToReviewService.update(task);
            }
            return "redirect:/manager/reviewtask";
        }catch (EntityNotFoundException err){
            model.addAttribute("error","Invalid Data");
            return "manager/review-task";
        }
    }
}
