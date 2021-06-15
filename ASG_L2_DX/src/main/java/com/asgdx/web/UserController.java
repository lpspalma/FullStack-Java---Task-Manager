package com.asgdx.web;

import com.asgdx.model.User;
import com.asgdx.repository.TaskRepository;
import com.asgdx.service.TaskService;
import com.asgdx.model.Task;
import com.asgdx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    TaskService taskService;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    TaskValidator taskValidator;


    private String assign = "manager/assign-task-manager";

    @GetMapping("manager/addtask")
    public String addTask(Model model) {
        model.addAttribute("taskForm", new Task());
        return "manager/add-task";
    }

    @PostMapping("manager/addtask")
    public String addTask(@ModelAttribute("taskForm") Task taskForm, BindingResult bindingResult,
                          Model model){
        taskValidator.validate(taskForm, bindingResult);
        if(bindingResult.hasErrors()){
            model.addAttribute("error","Invalid Data");
            return "manager/add-task";
        }
        taskService.save(taskForm);

        return "redirect:/manager/viewalltasks";
    }


    @GetMapping("manager/assigntask")
    public String assignTask() {
        return assign;
    }


    @PostMapping("manager/assigntask")
    public String assignTask(@RequestParam(name = "taskId") Long taskId,
                             @RequestParam(name = "employeeId")Long employeeId, Model model){
        if(taskId == null || employeeId == null){
            model.addAttribute("error","Invalid Details");
            return assign;
        }
        try{
            Task task = taskService.findById(taskId);
            User user = userService.findById(employeeId);
            task.setEmpId(user.getId());
            taskRepository.save(task);
            return "redirect:/manager/viewalltasks";
        }catch (EntityNotFoundException err){
            model.addAttribute("error","Invalid Data");
            return assign;
        }
    }

    @GetMapping("employee/change/{id}")
    public String changeTask(@PathVariable("id") Long taskId) {
        Task task = taskRepository.getById(taskId);
        task.setStatus("pending");
        taskRepository.save(task);

        return "redirect:/employee/status";
    }
}
